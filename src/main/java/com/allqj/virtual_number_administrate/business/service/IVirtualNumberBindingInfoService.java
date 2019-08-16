package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.vo.ModifyBindingInfoRequest;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberBindingInfoQueryResult;
import com.allqj.virtual_number_administrate.business.vo.VirtualNumberBindingQueryResult;

import java.util.List;

public interface IVirtualNumberBindingInfoService {
    List<VirtualNumberBindingInfoQueryResult> virtualNumberInfo(String phone);

    List<VirtualNumberBindingQueryResult> virtualNumber(String phone);

    Boolean modifyBindingInfo(ModifyBindingInfoRequest modifyBindingInfoRequest);
}
