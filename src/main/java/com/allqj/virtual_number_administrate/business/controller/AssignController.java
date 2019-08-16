package com.allqj.virtual_number_administrate.business.controller;

import com.allqj.virtual_number_administrate.business.service.IAssignService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "AssignController", description = "分配")
@RequestMapping("/assign")
@RestController
@ResultProxy
public class AssignController {

    @Autowired
    @Qualifier("assignServiceImpl")
    private IAssignService assignService;

    @ApiOperation("分配部门")
    @PostMapping("/assignDept")
    public synchronized ResultVO<AssignDeptResult> assignDept(@RequestBody AssignDeptRequest assignDeptRequest) {
        AssignDeptResult result = assignService.assignDept(assignDeptRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("取消分配部门")
    @PostMapping("/cancelAssignDept")
    public synchronized ResultVO<CancelAssignDeptResult> cancelAssignDept(@RequestBody CancelAssignDeptRequest cancelAssignDeptRequest) {
        CancelAssignDeptResult result = assignService.cancelAssignDept(cancelAssignDeptRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("按数量分配")
    @PostMapping("/numberAssign")
    public synchronized ResultVO<NumberAssignResult> numberAssign(@RequestBody NumberAssignRequest numberAssignRequest) {
        NumberAssignResult result = assignService.numberAssign(numberAssignRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("按数量取消分配")
    @PostMapping("/cancelNumberAssign")
    public synchronized ResultVO<CancelNumberAssignResult> cancelNumberAssign(@RequestBody CancelNumberAssignRequest cancelNumberAssignRequest) {
        CancelNumberAssignResult result = assignService.cancelNumberAssign(cancelNumberAssignRequest);
        return ResultVO.newInstance(result);
    }
}
