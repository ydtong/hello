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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 修改总机号的虚拟号数量
 */
@Service
public class MainPhoneVirtualNumberCountUpdateAllToMysqlBaseServiceImpl implements ICountModifyBaseService<Collection<SubAccountInfoQueryResult>, SubAccountInfoPageResult> {

    @Autowired
    private IShortService shortService;

    @Override
    public SubAccountInfoPageResult modify(Collection<SubAccountInfoQueryResult> subAccountInfoQueryResults) {

        List<SubAccountInfoModifyNumberRequest> subAccountInfoModifyRequestList = new ArrayList<>();
        subAccountInfoQueryResults.forEach(subAccountInfoQueryResult ->
        {
            SubAccountInfoModifyNumberRequest subAccountInfoModifyRequest = new SubAccountInfoModifyNumberRequest();
            BeanUtils.copyProperties(subAccountInfoQueryResult, subAccountInfoModifyRequest);
            subAccountInfoModifyRequestList.add(subAccountInfoModifyRequest);
        });

        Integer result = shortService.subAccountModifyNumberAll(subAccountInfoModifyRequestList).getResult();

        if (result == null)
            throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), StatusCodeEnum.REQUEST_ERROR.getMessage());

        return new SubAccountInfoPageResult();
    }
}
