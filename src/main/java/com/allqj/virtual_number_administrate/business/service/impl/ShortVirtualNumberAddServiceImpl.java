package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IAddBaseService;
import com.allqj.virtual_number_administrate.business.baseService.ICountModifyBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoPageResult;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoQueryResult;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.IVirtualNumberEsRepository;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberAddRequest;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberAddResult;
import com.allqj.virtual_number_administrate.business.vo.ShortVirtualNumberExcelAddRequest;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberAddRequest;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBase;
import com.allqj.virtual_number_administrate.util.IDUtils.CheckIdCardUtil;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@AddLog
@UseDistributedLocks
public class ShortVirtualNumberAddServiceImpl implements IVirtualNumberAddService<ShortVirtualNumberAddRequest, ShortVirtualNumberExcelAddRequest, ShortVirtualNumberAddResult> {
    @Autowired
    @Qualifier("shortVirtualNumberAddToMysqlBaseServiceImpl")
    private IAddBaseService<ShortVirtualNumberAddRequest, VirtualNumberMysqlEntity> shortVirtualNumberAddToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberAddToEsBaseServiceImpl")
    private IAddBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumberAddToEsBaseServiceImpl;
    @Autowired
    @Qualifier("virtualNumberModifyToMysqlBaseServiceImpl")
    private IModifyBaseService<VirtualNumberAddRequest, VirtualNumberMysqlEntity> virtualNumberModifyToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<VirtualNumberBase, VirtualNumberMysqlEntity> virtualNumberQueryFromMysqlBaseServiceImpl;

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    @Autowired
    @Qualifier("mainPhoneQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<Object, SubAccountInfoQueryResult> mainPhoneQueryFromMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("mainPhoneVirtualNumberCountUpdateToMysqlBaseServiceImpl")
    private ICountModifyBaseService<SubAccountInfoQueryResult, SubAccountInfoPageResult> mainPhoneVirtualNumberCountUpdateToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("mainPhoneVirtualNumberCountUpdateAllToMysqlBaseServiceImpl")
    private ICountModifyBaseService<Collection<SubAccountInfoQueryResult>, SubAccountInfoPageResult> mainPhoneVirtualNumberCountUpdateAllToMysqlBaseServiceImpl;

    @Autowired
    private IVirtualNumberEsRepository virtualNumberEsRepository;

    /**
     * 添加短虚拟号
     *
     * @param virtualNumberAddRequest
     * @return
     */
    @Override
    @Transactional
    @LogDescribe("添加短虚拟号")
    @Lock
    public ShortVirtualNumberAddResult add(ShortVirtualNumberAddRequest virtualNumberAddRequest) {
        excelAddCheck(null,
                virtualNumberAddRequest.getIdCard(),
                virtualNumberAddRequest.getVirtualNumber(),
                virtualNumberAddRequest.getSonVirtualNumber());

        //获取总机号
        SubAccountInfoQueryResult subAccountInfoQueryResult = mainPhoneQueryFromMysqlBaseServiceImpl.query(virtualNumberAddRequest.getAccountCode(), false, true);

        //总机号上限问题
        if (subAccountInfoQueryResult.getTotalCapacity() <= virtualNumberMysqlRepository.countByAccountCodeAndIsdeleteFalse(virtualNumberAddRequest.getAccountCode()))
            throw new ResultException(StatusCodeEnum.OUT_TOTAL_MAIN_PHONE.getCode(), StatusCodeEnum.OUT_TOTAL_MAIN_PHONE.getMessage());

        //添加Mysql数据库
        VirtualNumberMysqlEntity virtualNumberMysqlEntity = shortVirtualNumberAddToMysqlBaseServiceImpl.add(virtualNumberAddRequest, VirtualNumberTypeEnum.SHORT.getCode());
        //拼接返回结果
        ShortVirtualNumberAddResult shortVirtualNumberAddResult = new ShortVirtualNumberAddResult();
        BeanUtils.copyProperties(virtualNumberMysqlEntity, shortVirtualNumberAddResult);
        shortVirtualNumberAddResult.setMainPhone(subAccountInfoQueryResult.getMainPhone());

        //修改总机号
        Integer virtualNumberCount =
                virtualNumberMysqlRepository.countByAccountCodeAndIsdeleteFalse(subAccountInfoQueryResult.getAccountCode());
        subAccountInfoQueryResult.setVirtualNumberCount(virtualNumberCount);
        mainPhoneVirtualNumberCountUpdateToMysqlBaseServiceImpl.modify(subAccountInfoQueryResult);

        //同步到es
        virtualNumberAddToEsBaseServiceImpl.add(virtualNumberMysqlEntity, VirtualNumberTypeEnum.SHORT.getCode());
        return shortVirtualNumberAddResult;
    }

    /**
     * Excel添加短虚拟号
     *
     * @param virtualNumberAddRequestList
     * @return
     */
    @Override
    @Transactional
    @LogDescribe("Excel添加短虚拟号")
    @Lock
    public List<ShortVirtualNumberAddResult> excelAdd(List<ShortVirtualNumberExcelAddRequest> virtualNumberAddRequestList) {
        //按总机号分组Map
        Map<String, List<ShortVirtualNumberExcelAddRequest>> excelGroup = excelGroup(virtualNumberAddRequestList);
        //修改总机号集合
        List<SubAccountInfoQueryResult> subAccountInfoQueryResultList = new ArrayList<>();
        //MySQL虚拟号集合
        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();
        //ES虚拟号集合
        List<VirtualNumberEsEntity> virtualNumberEsEntityList = new ArrayList<>();
        //返回结果
        List<ShortVirtualNumberAddResult> shortVirtualNumberAddResultList = new ArrayList<>();
        for (Map.Entry<String, List<ShortVirtualNumberExcelAddRequest>> value : excelGroup.entrySet()) {
            //参数检查
            value.getValue().forEach(shortVirtualNumberExcelAddRequest ->
                    excelAddCheck(
                            shortVirtualNumberExcelAddRequest.getMainPhone(),
                            shortVirtualNumberExcelAddRequest.getIdCard(),
                            shortVirtualNumberExcelAddRequest.getVirtualNumber(),
                            shortVirtualNumberExcelAddRequest.getSonVirtualNumber()));
            //根据总机号查询总机信息
            SubAccountInfoQueryResult subAccountInfoQueryResult = mainPhoneQueryFromMysqlBaseServiceImpl.query(value.getKey(), false, true);
            //当前虚拟号数量
            Integer virtualNumber = virtualNumberMysqlRepository.countByAccountCodeAndIsdeleteFalse(subAccountInfoQueryResult.getAccountCode());
            subAccountInfoQueryResult.setVirtualNumberCount(virtualNumber);

            //保存虚拟号
            Date modifyTime = new Date();
            value.getValue().forEach(shortVirtualNumberExcelAddRequest -> {
                //查重?更新:新增
                VirtualNumberBase virtualNumberBase = new VirtualNumberBase();
                virtualNumberBase.setVirtualNumber(shortVirtualNumberExcelAddRequest.getVirtualNumber());
                virtualNumberBase.setVirtualNumberType(VirtualNumberTypeEnum.SHORT.getCode());
                VirtualNumberMysqlEntity virtualNumberMysqlEntity = virtualNumberQueryFromMysqlBaseServiceImpl.query(virtualNumberBase, false, false);
                if (null == virtualNumberMysqlEntity) {
                    //新增虚拟号
                    virtualNumberMysqlEntity = new VirtualNumberMysqlEntity();
                    BeanUtils.copyProperties(shortVirtualNumberExcelAddRequest, virtualNumberMysqlEntity);
                    virtualNumberMysqlEntity.setUtype(VirtualNumberTypeEnum.SHORT.getCode());
                    virtualNumberMysqlEntity.setAccountCode(subAccountInfoQueryResult.getAccountCode());
                    subAccountInfoQueryResult.setVirtualNumberCount(subAccountInfoQueryResult.getVirtualNumberCount() + 1);
                } else {
                    //更新联通长号
                    if (null != shortVirtualNumberExcelAddRequest.getSonVirtualNumber()) {
                        virtualNumberMysqlEntity.setSonVirtualNumber(shortVirtualNumberExcelAddRequest.getSonVirtualNumber().trim());
                    }
                    //更新身份证号
                    if (null != shortVirtualNumberExcelAddRequest.getIdCard()) {
                        virtualNumberMysqlEntity.setIdCard(shortVirtualNumberExcelAddRequest.getIdCard());
                    }
                    virtualNumberMysqlEntity.setModifytime(modifyTime);
                }
                VirtualNumberMysqlEntity result = virtualNumberMysqlRepository.save(virtualNumberMysqlEntity);
                virtualNumberMysqlEntityList.add(result);
            });
            //是否达到总机上限
            if (subAccountInfoQueryResult.getTotalCapacity() < subAccountInfoQueryResult.getVirtualNumberCount())
                throw new ResultException(StatusCodeEnum.OUT_TOTAL_MAIN_PHONE.getCode(), StatusCodeEnum.OUT_TOTAL_MAIN_PHONE.getMessage());
            subAccountInfoQueryResultList.add(subAccountInfoQueryResult);
        }

        // TODO  分布式事务？
        //修改总机号
        mainPhoneVirtualNumberCountUpdateAllToMysqlBaseServiceImpl.modify(subAccountInfoQueryResultList);

        //同步到ES并返回结果
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity -> {
            //同步ES
            VirtualNumberEsEntity virtualNumberEsEntity = new VirtualNumberEsEntity();
            BeanUtils.copyProperties(virtualNumberMysqlEntity, virtualNumberEsEntity);
            virtualNumberEsEntityList.add(virtualNumberEsEntity);
            //返回结果
            ShortVirtualNumberAddResult shortVirtualNumberAddResult = new ShortVirtualNumberAddResult();
            BeanUtils.copyProperties(virtualNumberMysqlEntity, shortVirtualNumberAddResult);
            shortVirtualNumberAddResultList.add(shortVirtualNumberAddResult);
        });
        // 批量save ES
        virtualNumberEsRepository.saveAll(virtualNumberEsEntityList);
        return shortVirtualNumberAddResultList;
    }

    //根据总机号进行分组
    private Map<String, List<ShortVirtualNumberExcelAddRequest>> excelGroup(List<ShortVirtualNumberExcelAddRequest> virtualNumberAddRequestList) {
        Map<String, List<ShortVirtualNumberExcelAddRequest>> groupMap = new HashMap<>();
        for (ShortVirtualNumberExcelAddRequest shortVirtualNumberExcelAddRequest : virtualNumberAddRequestList) {
            List<ShortVirtualNumberExcelAddRequest> shortVirtualNumberExcelAddRequestList = groupMap.get(shortVirtualNumberExcelAddRequest.getMainPhone());
            if (shortVirtualNumberExcelAddRequestList == null)
                shortVirtualNumberExcelAddRequestList = new ArrayList<>();
            shortVirtualNumberExcelAddRequestList.add(shortVirtualNumberExcelAddRequest);
            groupMap.put(shortVirtualNumberExcelAddRequest.getMainPhone(), shortVirtualNumberExcelAddRequestList);
        }
        return groupMap;
    }

    //号码长度验证
    private void excelAddCheck(String mainPhone, String idCard, String virtualNumber, String sonVirtualNumber) {
        Pattern pattern = Pattern.compile("[0-9]{11}");

        // 校验总机号  非必传非空长度11位
        if(null != mainPhone){
            if (mainPhone.trim().isEmpty() ||
                    !pattern.matcher(mainPhone).matches())
                throw new ResultException(StatusCodeEnum.MAIN_PHONE_TOO_SHORT.getCode(),
                        StatusCodeEnum.MAIN_PHONE_TOO_SHORT.getMessage() + " : " + mainPhone);
        }

        // 校验虚拟号  必传非空长度11位
        if (null == virtualNumber || virtualNumber.trim().isEmpty() ||
                !pattern.matcher(virtualNumber.trim()).matches())
            throw new ResultException(StatusCodeEnum.SHORT_VIRTUAL_NUMBER_TOO_SHORT.getCode(),
                    StatusCodeEnum.SHORT_VIRTUAL_NUMBER_TOO_SHORT.getMessage() + " : " + virtualNumber);

        // 长号长度非必传
        if (null != sonVirtualNumber) {
            sonVirtualNumber = sonVirtualNumber.trim();
            Matcher sonVirtualNumberMatcher = pattern.matcher(sonVirtualNumber);
            //联通长号长度
            if (!sonVirtualNumber.isEmpty() && !sonVirtualNumberMatcher.matches())
                throw new ResultException(StatusCodeEnum.LONG_VIRTUAL_NUMBER_TOO_SHORT.getCode(),
                        StatusCodeEnum.LONG_VIRTUAL_NUMBER_TOO_SHORT.getMessage() + " : " + sonVirtualNumber);
        }

        // 身份证非必传
        if (null != idCard) {
            //身份证校验
            idCard = idCard.trim();
            if (!idCard.isEmpty() && !CheckIdCardUtil.checkIdCard(idCard)) {
                throw new ResultException(StatusCodeEnum.CARD_NUMBER_ERROR.getCode(),
                        StatusCodeEnum.CARD_NUMBER_ERROR.getMessage() + " : " + idCard);
            }
        }

    }
}
