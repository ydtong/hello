package com.allqj.virtual_number_administrate.business.service.impl;

import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberModifyService;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberModifyIdCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author: cj
 * @description 短隐号修改实现
 * @date: 2019/4/30 13:56
 **/
@Service
public class ShortVirtualNumberModifyServiceImpl implements IVirtualNumberModifyService<VirtualNumberModifyIdCardRequest, ResultVO<Boolean>> {

    @Autowired
    @Qualifier("shortVirtualNumberModifyCardNumberServiceImpl")
    private IModifyBaseService<VirtualNumberModifyIdCardRequest, ResultVO<Boolean>> shortVirtualNumberModifyCardNumberServiceImpl;

    /**
     * 短隐号修改身份证号
     *
     * @param modifyIdCardRequest
     * @param virtualNumberType
     * @return
     */
    @Override
    public ResultVO<Boolean> modify(VirtualNumberModifyIdCardRequest modifyIdCardRequest, Integer virtualNumberType) {
        return shortVirtualNumberModifyCardNumberServiceImpl.modify(modifyIdCardRequest, virtualNumberType);
    }
}
