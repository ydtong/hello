package com.allqj.virtual_number_administrate.util.tokenVerification.base.authenticationCenter;


import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.tokenVerification.base.vo.TokenVo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "authentication")
public interface IAuthenticationCenter {
    //根据访问token获取刷新token
    @GetMapping(value = "/base/authentication/token/generateRefreshToken/{accessToken}")
    ResultVO<TokenVo> generateRefreshToken(@PathVariable(name = "accessToken") String accessToken);

    //根据刷新token生成新的访问token
    @GetMapping(value = "/base/authentication/token/generateNewAccessToken/{refreshToken}")
    ResultVO<TokenVo> generateNewAccessToken(@PathVariable(name = "refreshToken") String refreshToken);

    //校验访问token是否有效
    @GetMapping(value = "/base/authentication/token/verification/{accessToken}")
    ResultVO<Boolean> verification(@PathVariable(name = "accessToken") String accessToken);
}
