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

@Api(value = "ShortVirtualNumberBindingSubPageController", description = "短虚拟号占用子列表")
@RequestMapping("/shortVirtualNumberOccupySubPage")
@RestController
@ResultProxy
public class ShortVirtualNumberBindingSubPageController {

    @Autowired
    @Qualifier("shortVirtualNumberBindingSubPageServiceImpl")
    private IVirtualNumberSubPageService<ShortVirtualNumberBindingSubPageRequest, ShortVirtualNumberBindingSubPageResult> shortVirtualNumberBindingSubPageServiceImpl;

    @ApiOperation("虚拟号子列表")
    @PostMapping("/page")
    public ResultVO<PageResult<ShortVirtualNumberBindingSubPageResult>> page(@RequestBody QueryPageVO<ShortVirtualNumberBindingSubPageRequest> queryPageVO) {
        PageResult<ShortVirtualNumberBindingSubPageResult> result = shortVirtualNumberBindingSubPageServiceImpl.subPage(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号子列表表头")
    @GetMapping("/headers")
    public ResultVO<List<PageHeadersResult>> headers() {
        List<PageHeadersResult> result = shortVirtualNumberBindingSubPageServiceImpl.pageHeaders();
        return ResultVO.newInstance(result);
    }
}
