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

@Api(value = "ShortVirtualNumberFreeSubPageController", description = "短虚拟号空闲子列表")
@RequestMapping("/shortVirtualNumberFreeSubPage")
@RestController
@ResultProxy
public class ShortVirtualNumberFreeSubPageController {

    @Autowired
    @Qualifier("shortVirtualNumberFreeSubPageServiceImpl")
    private IVirtualNumberSubPageService<ShortVirtualNumberFreeSubPageRequest, ShortVirtualNumberFreeSubPageResult> shortVirtualNumberFreeSubPageServiceImpl;

    @ApiOperation("虚拟号子列表")
    @PostMapping("/page")
    public ResultVO<PageResult<ShortVirtualNumberFreeSubPageResult>> page(@RequestBody QueryPageVO<ShortVirtualNumberFreeSubPageRequest> queryPageVO) {
        PageResult<ShortVirtualNumberFreeSubPageResult> result = shortVirtualNumberFreeSubPageServiceImpl.subPage(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号子列表表头")
    @GetMapping("/headers")
    public ResultVO<List<PageHeadersResult>> headers() {
        List<PageHeadersResult> result = shortVirtualNumberFreeSubPageServiceImpl.pageHeaders();
        return ResultVO.newInstance(result);
    }
}
