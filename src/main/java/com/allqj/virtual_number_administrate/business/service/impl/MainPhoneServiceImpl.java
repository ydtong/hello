package com.allqj.virtual_number_administrate.business.service.impl;


import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte.IShortService;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.*;
import com.allqj.virtual_number_administrate.business.repository.mysql.IVirtualNumberMysqlRepository;
import com.allqj.virtual_number_administrate.business.service.IMainPhoneService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.headers.HeadersUtil;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@AddLog
@UseDistributedLocks
public class MainPhoneServiceImpl implements IMainPhoneService {

    @Autowired
    private IShortService shortService;

    @Autowired
    private IVirtualNumberMysqlRepository virtualNumberMysqlRepository;

    /**
     * 总机号添加
     *
     * @param mainPhoneAddRequest
     * @return
     */
    @Override
    @LogDescribe("总机号添加")
    @Lock
    public MainPhonePageResult add(MainPhoneAddRequest mainPhoneAddRequest) {
        Pattern pattern = Pattern.compile("[0-9]{11}");
        Matcher mainPhoneMatcher = pattern.matcher(mainPhoneAddRequest.getMainPhone());
        //总机号长度校验
        if (mainPhoneAddRequest.getMainPhone().isEmpty() && !mainPhoneMatcher.matches()) {
            throw new ResultException(StatusCodeEnum.MAIN_PHONE_TOO_SHORT.getCode(),
                    StatusCodeEnum.MAIN_PHONE_TOO_SHORT.getMessage() + " : " + mainPhoneAddRequest.getMainPhone());
        }
        SubAccountInfoAddRequest subAccountInfoAddRequest = new SubAccountInfoAddRequest();
        BeanUtils.copyProperties(mainPhoneAddRequest, subAccountInfoAddRequest);
        ResultVO<SubAccountInfoPageResult> result = shortService.subAccountAdd(subAccountInfoAddRequest);
        //基础服务添加失败
        if (result.getResult() == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), result.getMessage());
        MainPhonePageResult mainPhonePageResult = new MainPhonePageResult();
        BeanUtils.copyProperties(result.getResult(), mainPhonePageResult);
        return mainPhonePageResult;
    }

    /**
     * 总机号列表
     *
     * @param mainPhonePageRequest
     * @return
     */
    public PageResult<MainPhonePageResult> pageQuery(MainPhonePageRequest mainPhonePageRequest, PageVO pageInfo) {
        //拼接请求基础服务请求参数
        SubAccountPageRequest subAccountPageRequest = new SubAccountPageRequest();
        subAccountPageRequest.setMainPhone(mainPhonePageRequest.getMainPhone());
        subAccountPageRequest.setPage(pageInfo.getPage());
        subAccountPageRequest.setPageSize(pageInfo.getSize());
        ResultVO<PageResult<SubAccountInfoPageResult>> result = shortService.subAccountPageQuery(subAccountPageRequest);
        if (result.getResult() == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), result.getMessage());
        List<MainPhonePageResult> mainPhonePageResultList = new ArrayList<>();
        //拼接返回结果
        result.getResult().getContent().forEach(subAccountInfoPageResult ->
        {
            MainPhonePageResult mainPhonePageResult = new MainPhonePageResult();
            BeanUtils.copyProperties(subAccountInfoPageResult, mainPhonePageResult);
            mainPhonePageResult.setFreeCount(
                    subAccountInfoPageResult.getVirtualNumberCount() - subAccountInfoPageResult.getConsumeCapacity()
            );
            mainPhonePageResultList.add(mainPhonePageResult);
        });
        return new PageResult<>(
                mainPhonePageResultList,
                result.getResult().getPage(),
                result.getResult().getPageSize(),
                result.getResult().getCount(),
                result.getResult().getPages());
    }

    /**
     * 删除总机号
     *
     * @param mainPhoneDeleteRequest
     * @return
     */
    @Override
    @LogDescribe("删除总机号")
    @Lock
    public Boolean delete(MainPhoneDeleteRequest mainPhoneDeleteRequest) {
        ResultVO<Boolean> result = shortService.subAccountDelete(mainPhoneDeleteRequest.getAccountCode());
        if (result.getResult() == null || !result.getResult())
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), result.getMessage());
        return result.getResult();
    }

    /**
     * 总机号列表表头
     *
     * @return
     */
    public List<PageHeadersResult> pageHeader() {
        return HeadersUtil.getHeaders(MainPhonePageResult.class);
    }

    /**
     * 总机号修改
     *
     * @param mainPhoneModifyRequest
     * @return
     */
    @Override
    @LogDescribe("总机号修改")
    @Lock
    public MainPhonePageResult modify(MainPhoneModifyRequest mainPhoneModifyRequest) {
        //通过code查询总机号
        SubAccountInfoQueryResult subAccountInfoQueryResult =
                shortService.subAccountQueryFromAccountCode(mainPhoneModifyRequest.getAccountCode()).getResult();
        if (subAccountInfoQueryResult == null)
            throw new ResultException(StatusCodeEnum.NOT_MAIN_PHONE.getCode(), StatusCodeEnum.NOT_MAIN_PHONE.getMessage());

        // 号码池虚拟号数量必须小于等于号码池上限
        Integer totalVirtuals = virtualNumberMysqlRepository.countByAccountCodeAndIsdeleteFalse(mainPhoneModifyRequest.getAccountCode());
        if (totalVirtuals > mainPhoneModifyRequest.getTotalCapacity())
            throw new ResultException(StatusCodeEnum.TOTAL_CAPACITY_ERROR.getCode(), StatusCodeEnum.TOTAL_CAPACITY_ERROR.getMessage());
        //修改
        SubAccountInfoModifyRequest subAccountInfoModifyRequest = new SubAccountInfoModifyRequest();
        BeanUtils.copyProperties(subAccountInfoQueryResult, subAccountInfoModifyRequest);
        BeanUtils.copyProperties(mainPhoneModifyRequest, subAccountInfoModifyRequest);
        ResultVO<SubAccountInfoPageResult> result = shortService.subAccountModify(subAccountInfoModifyRequest);
        if (result.getResult() == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), result.getMessage());
        MainPhonePageResult mainPhonePageResult = new MainPhonePageResult();
        BeanUtils.copyProperties(result.getResult(), mainPhonePageResult);
        return mainPhonePageResult;
    }

    /**
     * 总机号字典
     *
     * @return
     */
    public List<DictionaryResult<Integer, String>> dictionary() {
        ResultVO<List<DictionaryResult<Integer, String>>> result = shortService.subAccountDictionary();
        if (result.getResult() == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), result.getMessage());
        return result.getResult();
    }

    /**
     * 通过code查询总机
     *
     * @param accountCode
     * @return
     */
    @Override
    @LogDescribe("通过code查询总机")
    public MainPhoneQueryResult mainPhoneQuery(Integer accountCode) {
        ResultVO<SubAccountInfoQueryResult> result = shortService.subAccountQueryFromAccountCode(accountCode);
        if (result.getResult() == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), result.getMessage());
        MainPhoneQueryResult mainPhoneQueryResult = new MainPhoneQueryResult();
        BeanUtils.copyProperties(result.getResult(), mainPhoneQueryResult);
        return mainPhoneQueryResult;
    }
}
