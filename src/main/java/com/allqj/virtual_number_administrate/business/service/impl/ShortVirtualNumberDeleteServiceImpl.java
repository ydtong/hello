package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.ICountModifyBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.VirtualNumberTypeEnum;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoPageResult;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoQueryResult;
import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.repository.mysql.entity.VirtualNumberMysqlEntity;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberDeleteService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.business.vo.base.VirtualNumberBase;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@AddLog
@UseDistributedLocks
public class ShortVirtualNumberDeleteServiceImpl implements IVirtualNumberDeleteService<ShortVirtualNumberDeleteRequest, ShortVirtualNumberDeleteResult> {

    @Autowired
    @Qualifier("virtualNumberQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<VirtualNumberBase, VirtualNumberMysqlEntity> virtualNumberQueryFromMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberModifyToEsBaseServiceImpl")
    private IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberEsEntity> virtualNumberModifyToEsBaseServiceImpl;

    @Autowired
    @Qualifier("virtualNumberDeleteToMysqlBaseServiceImpl")
    private IModifyBaseService<VirtualNumberMysqlEntity, VirtualNumberMysqlEntity> virtualNumberDeleteToMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("mainPhoneQueryFromMysqlBaseServiceImpl")
    private IQueryBaseService<Object, SubAccountInfoQueryResult> mainPhoneQueryFromMysqlBaseServiceImpl;

    @Autowired
    @Qualifier("mainPhoneVirtualNumberCountUpdateAllToMysqlBaseServiceImpl")
    private ICountModifyBaseService<Collection<SubAccountInfoQueryResult>, SubAccountInfoPageResult> mainPhoneVirtualNumberCountUpdateAllToMysqlBaseServiceImpl;

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    /**
     * 短号删除
     *
     * @param deleteVirtualNumberRequest
     * @return
     */
    @Override
    @Transactional
    @LogDescribe("短号删除")
    @Lock
    public ShortVirtualNumberDeleteResult deleteVirtualNumber(ShortVirtualNumberDeleteRequest deleteVirtualNumberRequest) {

        List<VirtualNumberMysqlEntity> virtualNumberMysqlEntityList = new ArrayList<>();
        Map<Integer, SubAccountInfoQueryResult> stringSubAccountInfoQueryResultMap = new HashMap<>();
        deleteVirtualNumberRequest.getVirtualNumberList().forEach(o ->
        {
            VirtualNumberMysqlEntity virtualNumberMysqlEntity =
                    virtualNumberQueryFromMysqlBaseServiceImpl.query(new VirtualNumberBase(o, VirtualNumberTypeEnum.SHORT.getCode()), false, true);
            virtualNumberMysqlEntity = virtualNumberDeleteToMysqlBaseServiceImpl.modify(virtualNumberMysqlEntity, VirtualNumberTypeEnum.SHORT.getCode());
            virtualNumberMysqlEntityList.add(virtualNumberMysqlEntity);

            //获取总机号
            SubAccountInfoQueryResult subAccountInfoQueryResult = stringSubAccountInfoQueryResultMap.get(virtualNumberMysqlEntity.getAccountCode());
            if (subAccountInfoQueryResult == null)
                subAccountInfoQueryResult = mainPhoneQueryFromMysqlBaseServiceImpl.query(virtualNumberMysqlEntity.getAccountCode(), false, true);

            stringSubAccountInfoQueryResultMap.put(virtualNumberMysqlEntity.getAccountCode(), subAccountInfoQueryResult);
        });

        //修改总机号
        stringSubAccountInfoQueryResultMap.values().forEach(subAccountInfoQueryResult ->
        {
            Integer virtualNumber = virtualNumberMysqlRepository.countByAccountCodeAndIsdeleteFalse(subAccountInfoQueryResult.getAccountCode());
            subAccountInfoQueryResult.setVirtualNumberCount(virtualNumber);
        });
        mainPhoneVirtualNumberCountUpdateAllToMysqlBaseServiceImpl.modify(stringSubAccountInfoQueryResultMap.values());

        //同步到es
        virtualNumberMysqlEntityList.forEach(virtualNumberMysqlEntity ->
                virtualNumberModifyToEsBaseServiceImpl.modify(virtualNumberMysqlEntity, VirtualNumberTypeEnum.SHORT.getCode()));

        return new ShortVirtualNumberDeleteResult();
    }
}
