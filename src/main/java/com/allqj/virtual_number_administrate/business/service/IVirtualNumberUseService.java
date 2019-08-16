package com.allqj.virtual_number_administrate.business.service;

import com.allqj.virtual_number_administrate.business.microService.vo.CallReqest;
import com.allqj.virtual_number_administrate.business.microService.vo.CallResult;
import com.allqj.virtual_number_administrate.business.vo.*;

public interface IVirtualNumberUseService {
    BindingVirtualNumberResult binding(BindingVirtualNumberRequest bindingVirtualNumberRequest);

    Boolean cancelBinding(CancelBindingVirtualNumberRequest cancelBindingVirtualNumberRequest);

    CallVirtualNumberResult calls(CallReqest callVirtualNumberRequest);
}
