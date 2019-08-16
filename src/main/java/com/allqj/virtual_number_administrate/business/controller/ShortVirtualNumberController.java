package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.baseService.IModifyBaseService;
import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.service.*;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.excelOperation.ExcelTemplateUtil;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import com.allqj.virtual_number_administrate.util.tokenVerification.aop.annotation.Token;
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

@Api(value = "ShortVirtualNumberController", description = "短虚拟号管理")
@RequestMapping("/shortVirtualNumber")
@RestController
@ResultProxy
public class ShortVirtualNumberController {
    @Autowired
    @Qualifier("shortVirtualNumberQueryServiceImpl")
    private IVirtualNumberQueryService shortVirtualNumberQueryServiceImpl;

    @Autowired
    @Qualifier("shortVirtualNumberPageServiceImpl")
    private IVirtualNumberPageService<ShortVirtualNumberPageRequest, ShortVirtualNumberPageResult> shortVirtualNumberPageServiceImpl;

    @Autowired
    @Qualifier("shortVirtualNumberAddServiceImpl")
    private IVirtualNumberAddService<ShortVirtualNumberAddRequest, ShortVirtualNumberExcelAddRequest, ShortVirtualNumberAddResult> shortVirtualNumberAddServiceImpl;

    @Autowired
    @Qualifier("shortVirtualNumberDeleteServiceImpl")
    private IVirtualNumberDeleteService<ShortVirtualNumberDeleteRequest, ShortVirtualNumberDeleteResult> shortVirtualNumberDeleteServiceImpl;
    @Autowired
    @Qualifier("shortVirtualNumberModifyCardNumberServiceImpl")
    private IModifyBaseService<VirtualNumberModifyIdCardRequest, ResultVO<Boolean>> shortVirtualNumberModifyCardNumberServiceImpl;

    @ApiOperation("虚拟号管理列表")
    @PostMapping("/page")
    public ResultVO<PageResult<ShortVirtualNumberPageResult>> page(@RequestBody QueryPageVO<ShortVirtualNumberPageRequest> queryPageVO) {
        PageResult<ShortVirtualNumberPageResult> result = shortVirtualNumberPageServiceImpl.page(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    @ApiOperation("虚拟号管理列表表头")
    @GetMapping("/pageHeaders")
    public ResultVO<List<PageHeadersResult>> pageHeaders() {
        List<PageHeadersResult> result = shortVirtualNumberPageServiceImpl.pageHeaders();
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
        ExcelTemplateUtil.setTemplate(response.getOutputStream(), ShortVirtualNumberExcelAddRequest.class);
    }

    @ApiOperation("Excel导入")
    @PutMapping("/template")
    public synchronized ResultVO<List<ShortVirtualNumberAddResult>> template(MultipartFile file) throws IOException, BiffException {
        Sheet sheet = ExcelTemplateUtil.openExcel(file.getInputStream(), 0);
        List<ShortVirtualNumberExcelAddRequest> requestList =
                ExcelTemplateUtil.readTemplate(sheet, ShortVirtualNumberExcelAddRequest.class);
        if (null == requestList || 0 == requestList.size())
            throw new ResultException(StatusCodeEnum.EXCEL_LACK.getCode(), StatusCodeEnum.EXCEL_LACK.getMessage());
        List<ShortVirtualNumberAddResult> result = shortVirtualNumberAddServiceImpl.excelAdd(requestList);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("短号添加")
    @PostMapping("/virtualNumber")
    public synchronized ResultVO<ShortVirtualNumberAddResult> virtualNumber(@RequestBody ShortVirtualNumberAddRequest shortVirtualNumberAddRequest) {
        ShortVirtualNumberAddResult result = shortVirtualNumberAddServiceImpl.add(shortVirtualNumberAddRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("删除短号")
    @PutMapping("/virtualNumber")
    public synchronized ResultVO<ShortVirtualNumberDeleteResult> virtualNumber(@RequestBody ShortVirtualNumberDeleteRequest shortVirtualNumberDeleteRequest) {
        ShortVirtualNumberDeleteResult result = shortVirtualNumberDeleteServiceImpl.deleteVirtualNumber(shortVirtualNumberDeleteRequest);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("根据虚拟号查询")
    @GetMapping("/queryVirtualNumber/{virtualNumber}")
    public ResultVO<Integer> queryVirtualNumber(@PathVariable("virtualNumber") String virtualNumber) {
        Integer result = shortVirtualNumberQueryServiceImpl.queryVirtualNumber(virtualNumber);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("根据子虚拟号查询")
    @GetMapping("/querySonVirtualNumber/{sonVirtualNumber}")
    public ResultVO<Integer> querySonVirtualNumber(@PathVariable("sonVirtualNumber") String sonVirtualNumber) {
        Integer result = shortVirtualNumberQueryServiceImpl.querySonVirtualNumber(sonVirtualNumber);
        return ResultVO.newInstance(result);
    }

    @ApiOperation("修改身份证号")
    @PostMapping("/modifyCardNumber")
    public ResultVO<Boolean> modifyCardNumber(@RequestBody VirtualNumberModifyIdCardRequest modifyIdCardRequest) {
        return shortVirtualNumberModifyCardNumberServiceImpl.modify(modifyIdCardRequest, modifyIdCardRequest.getVirtualNumberType());
    }
}
