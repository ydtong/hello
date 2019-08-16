package com.allqj.virtual_number_administrate.business.baseService.impl.mysql;

import com.allqj.virtual_number_administrate.business.baseService.IQueryBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.inte.IShortService;
import com.allqj.virtual_number_administrate.business.microService.shortHiddencall.vo.SubAccountInfoQueryResult;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 查询子账号
 */
@Service
public class MainPhoneQueryFromMysqlBaseServiceImpl implements IQueryBaseService<Object, SubAccountInfoQueryResult> {

    @Autowired
    private IShortService shortService;

    /**
     * 通过code或mainPhone查询总机信息
     *
     * @param arge
     * @param isdelete
     * @param must
     * @return
     */
    @Override
    public SubAccountInfoQueryResult query(Object arge, Boolean isdelete, Boolean must) {
        if (arge instanceof Integer)
            return query((Integer) arge, isdelete, must);
        if (arge instanceof String)
            return query((String) arge, isdelete, must);
        throw new ResultException(StatusCodeEnum.REQUEST_ERROR.getCode(), StatusCodeEnum.REQUEST_ERROR.getMessage());
    }

    /**
     * 通过总机code查询总机信息
     *
     * @param accountCode
     * @param isdelete
     * @param must
     * @return
     */
    private SubAccountInfoQueryResult query(Integer accountCode, Boolean isdelete, Boolean must) {
        ResultVO<SubAccountInfoQueryResult> subAccountInfoQueryResultResultVO =
                shortService.subAccountQueryFromAccountCode(accountCode);
        SubAccountInfoQueryResult subAccountInfoQueryResult = subAccountInfoQueryResultResultVO.getResult();
        if (must && subAccountInfoQueryResult == null)
            throw new ResultException(StatusCodeEnum.NOT_MAIN_PHONE.getCode(), subAccountInfoQueryResultResultVO.getMessage());
        return subAccountInfoQueryResult;
    }

    /**
     * 通过mainPhone查询总机信息
     *
     * @param mainPhone
     * @param isdelete
     * @param must
     * @return
     */
    private SubAccountInfoQueryResult query(String mainPhone, Boolean isdelete, Boolean must) {
        ResultVO<SubAccountInfoQueryResult> subAccountInfoQueryResultResultVO =
                shortService.subAccountQueryFromMainPhone(mainPhone);
        SubAccountInfoQueryResult subAccountInfoQueryResult = subAccountInfoQueryResultResultVO.getResult();
        if (must && subAccountInfoQueryResult == null)
            throw new ResultException(StatusCodeEnum.NOT_MAIN_PHONE.getCode(), subAccountInfoQueryResultResultVO.getMessage());
        return subAccountInfoQueryResult;
    }
}
