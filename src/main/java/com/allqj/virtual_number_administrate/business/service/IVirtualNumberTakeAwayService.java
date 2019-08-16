package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.vo.ModifyBindingInfoRequest;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.business.vo.VirtualTakeAwayResult;

/**
 * @author : lsy
 * @description: 部门调动时带走隐号
 * @date : 2019-04-24 9:31
 */
public interface IVirtualNumberTakeAwayService {
    ResultVO<Boolean> takeAwayVirtual(ModifyBindingInfoRequest modifyBindingInfoRequest);
}
