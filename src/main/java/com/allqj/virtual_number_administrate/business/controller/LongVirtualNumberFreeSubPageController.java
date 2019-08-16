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

@Api(value = "LongVirtualNumberFreeSubPageController", description = "长虚拟号空闲子列表")
@RequestMapping("/longVirtualNumberFreeSubPage")
@RestController
@ResultProxy
public class LongVirtualNumberFreeSubPageController {

    @Autowired
    @Qualifier("longVirtualNumberFreeSubPageServiceImpl")
    private IVirtualNumberSubPageService<LongVirtualNumberFreeSubPageRequest, LongVirtualNumberFreeSubPageResult> longVirtualNumberFreeSubPageServiceImpl;

    @ApiOperation("虚拟号子列表")
    @PostMapping("/page")
    public ResultVO<PageResult<LongVirtualNumberFreeSubPageResult>> page(@RequestBody QueryPageVO<LongVirtualNumberFreeSubPageRequest> queryPageVO) {
        PageResult<LongVirtualNumberFreeSubPageResult> result = longVirtualNumberFreeSubPageServiceImpl.subPage(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号子列表表头")
    @GetMapping("/headers")
    public ResultVO<List<PageHeadersResult>> headers() {
        List<PageHeadersResult> result = longVirtualNumberFreeSubPageServiceImpl.pageHeaders();
        return ResultVO.newInstance(result);
    }
}
