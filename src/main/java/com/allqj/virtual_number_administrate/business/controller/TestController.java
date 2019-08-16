package com.allqj.virtual_number_administrate.business.controller;


import com.allqj.virtual_number_administrate.business.service.ITestService;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "TestController", description = "测试接口")
@RequestMapping("/test")
@RestController
@ResultProxy
public class TestController {

    @Autowired
    private ITestService testService;

    @GetMapping("/test")
    public ResultVO<String> test() {
        testService.test();
        return ResultVO.newInstance("Test");
    }

}
