package com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte;


import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.*;
import com.allqj.virtual_number_administrate.business.microService.vo.CallReqest;
import com.allqj.virtual_number_administrate.business.microService.vo.CallResult;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import com.allqj.virtual_number_administrate.business.vo.PageResult;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.List;

//,url = "http://192.168.10.200:8091"
@Component
@FeignClient(name = "hiddencallbase")
public interface IShortServiceClient {

    //绑定号码
    @PostMapping(value = "/platformBaseHiddenCall/business/enterprises/createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<Boolean> binding(@RequestBody ShortBindingRequest shortBindingRequest);

    //解绑号码
    @GetMapping("/platformBaseHiddenCall/business/enterprises/dropUser/{phone}")
    ResultVO<Boolean> dropUser(@PathVariable(value = "phone") String phone);

    //呼叫
    @PostMapping(value = "/longHiddenCall/basicServices/onlinecall/operation", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<CallResult> call(@RequestBody CallReqest callReqest);

    //总机号添加
    @PostMapping(value = "/platformBaseHiddenCall/business/subAccount/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<SubAccountInfoPageResult> subAccountAdd(@RequestBody SubAccountInfoAddRequest subAccountInfoAddRequest);

    //总机号列表
    @PostMapping(value = "/platformBaseHiddenCall/business/subAccount/pageQuery", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<PageResult<SubAccountInfoPageResult>> subAccountPageQuery(@RequestBody SubAccountPageRequest mainPhonePageResult);

    //删除总机号
    @PostMapping(value = "/platformBaseHiddenCall/business/subAccount/delete/{accountCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<Boolean> subAccountDelete(@PathVariable("accountCode") Integer accountCode);

    //总机号修改
    @PostMapping(value = "/platformBaseHiddenCall/business/subAccount/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<SubAccountInfoPageResult> subAccountModify(@RequestBody SubAccountInfoModifyRequest subAccountInfoModifyRequest);

    //总机号批量修改
    @PostMapping(value = "/platformBaseHiddenCall/business/subAccount/modifyAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<Integer> subAccountModifyAll(@RequestBody Collection<SubAccountInfoModifyRequest> subAccountInfoModifyRequestList);

    //总机号修改
    @PostMapping(value = "/platformBaseHiddenCall/business/subAccount/modifyNumber", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<SubAccountInfoPageResult> subAccountModifyNumber(@RequestBody SubAccountInfoModifyNumberRequest subAccountInfoModifyRequest);

    //总机号批量修改
    @PostMapping(value = "/platformBaseHiddenCall/business/subAccount/modifyNumberAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultVO<Integer> subAccountModifyNumberAll(@RequestBody Collection<SubAccountInfoModifyNumberRequest> subAccountInfoModifyRequestList);

    //总机号字典
    @GetMapping("/platformBaseHiddenCall/business/subAccount/dictionary")
    ResultVO<List<DictionaryResult<Integer, String>>> subAccountDictionary();

    //通过code查询总机
    @GetMapping("/platformBaseHiddenCall/business/subAccount/queryFromAccountCode/{accountCode}")
    ResultVO<SubAccountInfoQueryResult> subAccountQueryFromAccountCode(@PathVariable("accountCode") Integer accountCode);

    //通过总机号查询总机
    @GetMapping("/platformBaseHiddenCall/business/subAccount/queryFromMainPhone/{mainPhone}")
    ResultVO<SubAccountInfoQueryResult> subAccountQueryFromMainPhone(@PathVariable("mainPhone") String mainPhone);

    //通过code查询总机
    @GetMapping("/platformBaseHiddenCall/business/subAccount/queryNumberFromAccountCode/{accountCode}")
    ResultVO<SubAccountInfoQueryNumberResult> subAccountQueryNumberFromAccountCode(@PathVariable("accountCode") Integer accountCode);

    //通过总机号查询总机
    @GetMapping("/platformBaseHiddenCall/business/subAccount/queryNumberFromMainPhone/{mainPhone}")
    ResultVO<SubAccountInfoQueryNumberResult> subAccountQueryNumberFromMainPhone(@PathVariable("mainPhone") String mainPhone);

    //根据总机号模糊查询
    @GetMapping("/platformBaseHiddenCall/business/subAccount/queryMainPhoneLike/{mainPhone}")
    ResultVO<List<SubAccountInfoPageResult>> subAccountLikeMainPhone(@PathVariable("mainPhone") String mainPhone);
}
