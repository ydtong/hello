package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberAddService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberDeleteService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberPageService;
import com.allqj.virtual_number_administrate.business.service.IVirtualNumberQueryService;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.excelOperation.ExcelTemplateUtil;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(value = "LongVirtualNumberController", description = "长虚拟号管理")
@RequestMapping("/longVirtualNumber")
@RestController
@ResultProxy
public class LongVirtualNumberController {
    @Autowired
    @Qualifier("longVirtualNumberQueryServiceImpl")
    private IVirtualNumberQueryService longVirtualNumberQueryServiceImpl;

    @Autowired
    @Qualifier("longVirtualNumberPageServiceImpl")
    private IVirtualNumberPageService<LongVirtualNumberPageRequest, LongVirtualNumberPageResult> longVirtualNumberPageServiceImpl;

    @Autowired
    @Qualifier("longVirtualNumberAddServiceImpl")
    private IVirtualNumberAddService<LongVirtualNumberAddRequest, LongVirtualNumberExcelAddRequest, LongVirtualNumberAddResult> longVirtualNumberAddServiceImpl;

    @Autowired
    @Qualifier("longVirtualNumberDeleteServiceImpl")
    private IVirtualNumberDeleteService<LongVirtualNumberDeleteRequest, LongVirtualNumberDeleteResult> longVirtualNumberDeleteServiceImpl;

    @ApiOperation("虚拟号管理列表")
    @PostMapping("/page")
    public ResultVO<PageResult<LongVirtualNumberPageResult>> page(@RequestBody QueryPageVO<LongVirtualNumberPageRequest> queryPageVO) {
        PageResult<LongVirtualNumberPageResult> result = longVirtualNumberPageServiceImpl.page(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号管理列表表头")
    @GetMapping("/pageHeaders")
    public ResultVO<List<PageHeadersResult>> pageHeaders() {
        List<PageHeadersResult> result = longVirtualNumberPageServiceImpl.pageHeaders();
        return ResultVO.newInstance(result);
    }

    @ApiOperation("Excel模板下载")
    @GetMapping("/template")
    public void template(HttpServletResponse response) throws IOException, WriteException {
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-type", "text/html;charset=UTF-8");
        response.addHeader("Content-disposition", "attachment;filename=excel.xls");
        response.flushBuffer();
        ExcelTemplateUtil.setTemplate(response.getOutputStream(), LongVirtualNumberAddRequest.class);
    }

    @ApiOperation("Excel导入")
    @PutMapping("/template")
    public synchronized ResultVO<List<LongVirtualNumberAddResult>> template(MultipartFile file) throws IOException, BiffException {
        Sheet sheet = ExcelTemplateUtil.openExcel(file.getInputStream(), 0);
        List<LongVirtualNumberExcelAddRequest> requestList =
                ExcelTemplateUtil.readTemplate(sheet, LongVirtualNumberExcelAddRequest.class);
        //校验是否为空
        if (null == requestList || 0 == requestList.size())
            throw new ResultException(StatusCodeEnum.EXCEL_LACK.getCode(), StatusCodeEnum.EXCEL_LACK.getMessage());
        List<LongVirtualNumberAddResult> result = longVirtualNumberAddServiceImpl.excelAdd(requestList);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("长号添加")
    @PostMapping("/virtualNumber")
    public synchronized ResultVO<LongVirtualNumberAddResult> virtualNumber(@RequestBody LongVirtualNumberAddRequest longVirtualNumberAddRequest) {
        LongVirtualNumberAddResult result = longVirtualNumberAddServiceImpl.add(longVirtualNumberAddRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("删除长号")
    @PutMapping("/virtualNumber")
    public synchronized ResultVO<LongVirtualNumberDeleteResult> virtualNumber(@RequestBody LongVirtualNumberDeleteRequest longVirtualNumberDeleteRequest) {
        LongVirtualNumberDeleteResult result = longVirtualNumberDeleteServiceImpl.deleteVirtualNumber(longVirtualNumberDeleteRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("根据虚拟号查询")
    @GetMapping("/queryVirtualNumber/{virtualNumber}")
    public ResultVO<Integer> queryVirtualNumber(@PathVariable("virtualNumber") String virtualNumber) {
        Integer result = longVirtualNumberQueryServiceImpl.queryVirtualNumber(virtualNumber);
        return ResultVO.newInstance(result);
    }
}
