package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.service.IVirtualNumberHistoryService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "LongVirtualNumberHistoryController", description = "长虚拟号绑定历史")
@RequestMapping("/longVirtualNumberHistory")
@RestController
@ResultProxy
public class LongVirtualNumberHistoryController {
    @Autowired
    @Qualifier("longVirtualNumberHistoryServiceImpl")
    private IVirtualNumberHistoryService<LongVirtualNumberHistoryRequest, LongVirtualNumberHistoryResult> longVirtualNumberHistoryServiceImpl;

    @ApiOperation("绑定历史列表")
    @PostMapping("/historyPage")
    public ResultVO<PageResult<LongVirtualNumberHistoryResult>> page(@RequestBody QueryPageVO<LongVirtualNumberHistoryRequest> queryPageVO) {
        PageResult<LongVirtualNumberHistoryResult> result = longVirtualNumberHistoryServiceImpl.page(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("绑定历史列表表头")
    @GetMapping("/pageHeaders")
    public ResultVO<List<PageHeadersResult>> pageHeaders() {
        List<PageHeadersResult> result = longVirtualNumberHistoryServiceImpl.pageHeaders();
        return ResultVO.newInstance(result);
    }
}
