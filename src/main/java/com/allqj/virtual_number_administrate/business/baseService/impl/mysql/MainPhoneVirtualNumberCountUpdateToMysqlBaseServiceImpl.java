package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.ICountModifyBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte.IShortService;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoModifyNumberRequest;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoPageResult;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoQueryResult;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 修改总机号的虚拟号数量
 */
@Service
public class MainPhoneVirtualNumberCountUpdateToMysqlBaseServiceImpl implements ICountModifyBaseService<SubAccountInfoQueryResult, SubAccountInfoPageResult> {

    @Autowired
    private IShortService shortService;

    @Override
    public SubAccountInfoPageResult modify(SubAccountInfoQueryResult subAccountInfoQueryResult) {

        SubAccountInfoModifyNumberRequest subAccountInfoModifyRequest = new SubAccountInfoModifyNumberRequest();
        BeanUtils.copyProperties(subAccountInfoQueryResult, subAccountInfoModifyRequest);

        SubAccountInfoPageResult subAccountInfoPageResult = shortService.subAccountModifyNumber(subAccountInfoModifyRequest).getResult();
        if (subAccountInfoPageResult == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), StatusCodeEnum.REQUEST_ERROR.getMessage());
        return subAccountInfoPageResult;
    }
}
