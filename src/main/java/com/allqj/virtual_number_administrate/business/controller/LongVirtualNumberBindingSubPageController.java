package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.service.IVirtualNumberSubPageService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "LongVirtualNumberBindingSubPageController", description = "长虚拟号占用子列表")
@RequestMapping("/longVirtualNumberOccupySubPage")
@RestController
@ResultProxy
public class LongVirtualNumberBindingSubPageController {

    @Autowired
    @Qualifier("longVirtualNumberBindingSubPageServiceImpl")
    private IVirtualNumberSubPageService<LongVirtualNumberBindingSubPageRequest, LongVirtualNumberBindingSubPageResult> longVirtualNumberBindingSubPageServiceImpl;

    @ApiOperation("虚拟号子列表")
    @PostMapping("/page")
    public ResultVO<PageResult<LongVirtualNumberBindingSubPageResult>> page(@RequestBody QueryPageVO<LongVirtualNumberBindingSubPageRequest> queryPageVO) {
        PageResult<LongVirtualNumberBindingSubPageResult> result = longVirtualNumberBindingSubPageServiceImpl.subPage(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号子列表表头")
    @GetMapping("/headers")
    public ResultVO<List<PageHeadersResult>> headers() {
        List<PageHeadersResult> result = longVirtualNumberBindingSubPageServiceImpl.pageHeaders();
        return ResultVO.newInstance(result);
    }
}
