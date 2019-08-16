package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.enums.UserBindingStateEnum;
import com.allqj.virtual_number_administrate.business.microService.vo.CallReqest;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberBindingInfoService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberTakeAwayService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberUseService;
import com.allqj.virtual_number_administrate.business.service.impl.VirtualNumberTakeAwayImpl;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "VirtualNumberUseController", description = "虚拟号使用")
@RequestMapping("/virtualNumberUse")
@RestController
@ResultProxy
public class VirtualNumberUseController {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier("virtualNumberBindingInfoServiceImpl")
    private IVirtualNumberBindingInfoService virtualNumberBindingInfoServiceImpl;

    @ApiOperation("绑定")
    @PostMapping("/binding")
    public synchronized ResultVO<BindingVirtualNumberResult> binding(@RequestBody BindingVirtualNumberRequest bindingVirtualNumberRequest) {
        IVirtualNumberUseService virtualNumberUseService = applicationContext.getBean(UserBindingStateEnum.getImplType(bindingVirtualNumberRequest.getVirtualNumberType()));
        BindingVirtualNumberResult result = virtualNumberUseService.binding(bindingVirtualNumberRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("取消绑定")
    @PutMapping("/cancelBinding")
    public synchronized ResultVO<Boolean> cancelBinding(@RequestBody CancelBindingVirtualNumberRequest cancelBindingVirtualNumberRequest) {
        IVirtualNumberUseService virtualNumberUseService = applicationContext.getBean(UserBindingStateEnum.getImplType(cancelBindingVirtualNumberRequest.getVirtualNumberType()));
        Boolean result = virtualNumberUseService.cancelBinding(cancelBindingVirtualNumberRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("打电话")
    @PostMapping("/call")
    public ResultVO<CallVirtualNumberResult> call(@RequestBody CallReqest callReqest) {
        IVirtualNumberUseService virtualNumberUseService = applicationContext.getBean(UserBindingStateEnum.getImplType(callReqest.getVirtualNumberType()));
        CallVirtualNumberResult result = virtualNumberUseService.calls(callReqest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("查询绑定的虚拟号详细信息")
    @GetMapping("/virtualNumberInfo/{phone}")
    public ResultVO<List<VirtualNumberBindingInfoQueryResult>> virtualNumberInfoQuery(@PathVariable("phone") String phone) {
        List<VirtualNumberBindingInfoQueryResult> result = virtualNumberBindingInfoServiceImpl.virtualNumberInfo(phone);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("查询绑定的虚拟号")
    @GetMapping("/virtualNumber/{phone}")
    public ResultVO<List<VirtualNumberBindingQueryResult>> virtualNumberQuery(@PathVariable("phone") String phone) {
        List<VirtualNumberBindingQueryResult> result = virtualNumberBindingInfoServiceImpl.virtualNumber(phone);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("部门调动修改绑定信息")
    @PostMapping("/modifyBindingInfo")
    public synchronized ResultVO<Boolean> newModifyBindingInfo(@RequestBody ModifyBindingInfoRequest modifyBindingInfoRequest) {
        IVirtualNumberTakeAwayService virtualNumberTakeAwayService = applicationContext.getBean(VirtualNumberTakeAwayImpl.class);
        return virtualNumberTakeAwayService.takeAwayVirtual(modifyBindingInfoRequest);
    }

}
