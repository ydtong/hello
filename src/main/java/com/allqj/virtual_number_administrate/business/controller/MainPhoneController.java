package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.service.IMainPhoneService;
import com.allqj.virtual_number_administrate.business.vo.MainPhoneQueryResult;
import com.allqj.virtual_number_administrate.business.vo.*;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "MainPhoneController", description = "总机号管理")
@RequestMapping("/mainPhone")
@RestController
@ResultProxy
public class MainPhoneController {

    @Autowired
    private IMainPhoneService mainPhoneService;

    /**
     * 总机号添加
     *
     * @param mainPhoneAddRequest
     * @return
     */
    @ApiOperation("总机号添加")
    @PostMapping("/platformBaseHiddenCall/business/subAccount/add")
    public synchronized ResultVO<MainPhonePageResult> add(@RequestBody MainPhoneAddRequest mainPhoneAddRequest) {
        MainPhonePageResult result = mainPhoneService.add(mainPhoneAddRequest);
        return ResultVO.newInstance(result);
    }

    /**
     * 总机号列表
     *
     * @param queryPageVO
     * @return
     */
    @ApiOperation("总机号列表")
    @PostMapping("/pageQuery")
    public ResultVO<PageResult<MainPhonePageResult>> pageQuery(@RequestBody QueryPageVO<MainPhonePageRequest> queryPageVO) {
        PageResult<MainPhonePageResult> result = mainPhoneService.pageQuery(queryPageVO.getQuery(), queryPageVO.getPageInfo());
        return ResultVO.newInstance(result);
    }

    /**
     * 删除总机号
     *
     * @param mainPhoneDeleteRequest
     * @return
     */
    @ApiOperation("删除总机号")
    @PutMapping("/delete")
    public synchronized ResultVO<Boolean> delete(@RequestBody MainPhoneDeleteRequest mainPhoneDeleteRequest) {
        Boolean result = mainPhoneService.delete(mainPhoneDeleteRequest);
        return ResultVO.newInstance(result);
    }

    /**
     * 总机号列表表头
     *
     * @return
     */
    @ApiOperation("总机号列表表头")
    @GetMapping("/pageHeader")
    public ResultVO<List<PageHeadersResult>> pageHeader() {
        List<PageHeadersResult> result = mainPhoneService.pageHeader();
        return ResultVO.newInstance(result);
    }

    /**
     * 总机号修改
     *
     * @param mainPhoneModifyRequest
     * @return
     */
    @ApiOperation("总机号修改")
    @PostMapping("/modify")
    public synchronized ResultVO<MainPhonePageResult> modify(@RequestBody MainPhoneModifyRequest mainPhoneModifyRequest) {
        MainPhonePageResult result = mainPhoneService.modify(mainPhoneModifyRequest);
        return ResultVO.newInstance(result);
    }

    /**
     * 总机号字典
     *
     * @return
     */
    @ApiOperation("总机号字典")
    @GetMapping("/dictionary")
    public ResultVO<List<DictionaryResult<Integer, String>>> dictionary() {
        List<DictionaryResult<Integer, String>> result = mainPhoneService.dictionary();
        return ResultVO.newInstance(result);
    }

    /**
     * 通过code查询总机
     *
     * @param accountCode
     * @return
     */
    @ApiOperation("通过code查询总机")
    @GetMapping("/mainPhoneQuery/{accountCode}")
    public ResultVO<MainPhoneQueryResult> mainPhoneQuery(@PathVariable("accountCode") Integer accountCode) {
        MainPhoneQueryResult result = mainPhoneService.mainPhoneQuery(accountCode);
        return ResultVO.newInstance(result);
    }
}
