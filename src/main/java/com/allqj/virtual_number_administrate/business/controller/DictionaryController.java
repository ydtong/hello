package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.service.IDictionaryService;
import com.allqj.virtual_number_administrate.business.vo.DictionaryResult;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import com.allqj.virtual_number_administrate.util.tokenVerification.aop.annotation.Token;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "DictionaryController", description = "字典")
@RequestMapping("/dictionary")
@RestController
@ResultProxy
public class DictionaryController {

    @Autowired
    private IDictionaryService dictionaryService;

    @ApiOperation("虚拟号类型")
    @GetMapping("/virtualNumberType")
    public ResultVO<List<DictionaryResult<Integer, String>>> virtualNumberType() {
        List<DictionaryResult<Integer, String>> result = dictionaryService.virtualNumberType();
        return ResultVO.newInstance(result);
    }

    @ApiOperation("部门虚拟号查询条件")
    @GetMapping("/deptVirtualNumberWhere")
    public ResultVO<List<DictionaryResult<Integer, String>>> deptVirtualNumberWhere() {
        List<DictionaryResult<Integer, String>> result = dictionaryService.deptVirtualNumberWhere();
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号分配状态")
    @GetMapping("/virtualNumberAssignState")
    public ResultVO<List<DictionaryResult<Boolean, String>>> virtualNumberAssignState() {
        List<DictionaryResult<Boolean, String>> result = dictionaryService.virtualNumberAssignState();
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号绑定状态")
    @GetMapping("/virtualNumberBindingState")
    public ResultVO<List<DictionaryResult<Boolean, String>>> virtualNumberBindingState() {
        List<DictionaryResult<Boolean, String>> result = dictionaryService.virtualNumberBindingState();
        return ResultVO.newInstance(result);
    }

    @ApiOperation("联通长号存在状态")
    @GetMapping("/SonVirtualNumberState")
    public ResultVO<List<DictionaryResult<Integer, String>>> sonVirtualNumberState() {
        List<DictionaryResult<Integer, String>> result = dictionaryService.sonVirtualNumberState();
        return ResultVO.newInstance(result);
    }
}
