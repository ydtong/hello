package com.allqj.virtual_number_administrate.business.microService.shortHiddencall.impl;


import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte.IShortService;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte.IShortServiceClient;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.*;
import com.allqj.virtual_number_administrate.business.microService.vo.CallReqest;
import com.allqj.virtual_number_administrate.business.microService.vo.CallResult;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import com.allqj.virtual_number_administrate.business.vo.PageResult;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;


@AddLog
@Service
public class ShortService implements IShortService {

    @SuppressWarnings("all")
    @Autowired
    private IShortServiceClient shortServiceClient;

    @Override
    @LogDescribe("短号基础服务，绑定号码")
    public ResultVO<Boolean> binding(ShortBindingRequest shortBindingRequest) {
        return shortServiceClient.binding(shortBindingRequest);
    }

    @Override
    @LogDescribe("短号基础服务，解绑号码")
    public ResultVO<Boolean> dropUser(String phone) {
        return shortServiceClient.dropUser(phone);
    }

    @Override
    @LogDescribe("短号基础服务，呼叫")
    public ResultVO<CallResult> call(CallReqest callReqest) {
        return shortServiceClient.call(callReqest);
    }

    @Override
    @LogDescribe("总机号添加")
    public ResultVO<SubAccountInfoPageResult> subAccountAdd(SubAccountInfoAddRequest subAccountInfoAddRequest) {
        return shortServiceClient.subAccountAdd(subAccountInfoAddRequest);
    }

    @Override
    @LogDescribe("总机号列表")
    public ResultVO<PageResult<SubAccountInfoPageResult>> subAccountPageQuery(SubAccountPageRequest mainPhonePageResult) {
        return shortServiceClient.subAccountPageQuery(mainPhonePageResult);
    }

    @Override
    @LogDescribe("删除总机号")
    public ResultVO<Boolean> subAccountDelete(Integer accountCode) {
        return shortServiceClient.subAccountDelete(accountCode);
    }

    @Override
    @LogDescribe("总机号修改")
    public ResultVO<SubAccountInfoPageResult> subAccountModify(SubAccountInfoModifyRequest subAccountInfoModifyRequest) {
        return shortServiceClient.subAccountModify(subAccountInfoModifyRequest);
    }

    @Override
    @LogDescribe("总机号批量修改")
    public ResultVO<Integer> subAccountModifyAll(Collection<SubAccountInfoModifyRequest> subAccountInfoModifyRequestList) {
        return shortServiceClient.subAccountModifyAll(subAccountInfoModifyRequestList);
    }

    @Override
    @LogDescribe("总机号修改")
    public ResultVO<SubAccountInfoPageResult> subAccountModifyNumber(SubAccountInfoModifyNumberRequest subAccountInfoModifyRequest) {
        return shortServiceClient.subAccountModifyNumber(subAccountInfoModifyRequest);
    }

    @Override
    @LogDescribe("总机号批量修改")
    public ResultVO<Integer> subAccountModifyNumberAll(Collection<SubAccountInfoModifyNumberRequest> subAccountInfoModifyRequestList) {
        return shortServiceClient.subAccountModifyNumberAll(subAccountInfoModifyRequestList);
    }

    @Override
    @LogDescribe("总机号字典")
    public ResultVO<List<DictionaryResult<Integer, String>>> subAccountDictionary() {
        return shortServiceClient.subAccountDictionary();
    }

    @Override
    @LogDescribe("通过code查询总机")
    public ResultVO<SubAccountInfoQueryResult> subAccountQueryFromAccountCode(Integer accountCode) {
        return shortServiceClient.subAccountQueryFromAccountCode(accountCode);
    }

    @Override
    @LogDescribe("通过总机号查询总机")
    public ResultVO<SubAccountInfoQueryResult> subAccountQueryFromMainPhone(String mainPhone) {
        return shortServiceClient.subAccountQueryFromMainPhone(mainPhone);
    }

    @Override
    @LogDescribe("通过code查询总机")
    public ResultVO<SubAccountInfoQueryNumberResult> subAccountQueryNumberFromAccountCode(Integer accountCode) {
        return shortServiceClient.subAccountQueryNumberFromAccountCode(accountCode);
    }

    @Override
    @LogDescribe("通过总机号查询总机")
    public ResultVO<SubAccountInfoQueryNumberResult> subAccountQueryNumberFromMainPhone(String mainPhone) {
        return shortServiceClient.subAccountQueryNumberFromMainPhone(mainPhone);
    }

    @Override
    @LogDescribe("根据总机号模糊查询")
    public ResultVO<List<SubAccountInfoPageResult>> subAccountLikeMainPhone(String mainPhone) {
        return shortServiceClient.subAccountLikeMainPhone(mainPhone);
    }
}
