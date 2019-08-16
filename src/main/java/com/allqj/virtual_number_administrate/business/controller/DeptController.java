package com.allqj.virtual_number_administrate.business.controller;

import com.allqj.virtual_number_administrate.business.service.IDeptService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "DeptVirtualNumberController", description = "部门隐号")
@RequestMapping("/deptVirtualNumber")
@RestController
@ResultProxy
public class DeptController {

    @Autowired
    @Qualifier("deptServiceImpl")
    private IDeptService deptService;

    @ApiOperation("部门列表")
    @PostMapping("/page")
    public ResultVO<PageResult<DeptPageResult>> page(@RequestBody QueryPageVO<DeptPageRequest> queryPageVO) {
        PageResult<DeptPageResult> result = deptService.page(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("部门列表表头")
    @GetMapping("/pageHeaders")
    public ResultVO<List<PageHeadersResult>> pageHeaders() {
        List<PageHeadersResult> result = deptService.pageHeaders();
        return ResultVO.newInstance(result);
    }
}
