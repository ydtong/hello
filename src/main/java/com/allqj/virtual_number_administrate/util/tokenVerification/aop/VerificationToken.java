package com.allqj.virtual_number_administrate.util.tokenVerification.aop;

import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.redis.IRedis;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import com.allqj.virtual_number_administrate.util.tokenVerification.base.authenticationCenter.IAuthenticationCenter;
import com.allqj.virtual_number_administrate.util.tokenVerification.base.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class VerificationToken {
    @Resource
    private IAuthenticationCenter authenticationCenter;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    @Qualifier("stringRedisTemplateImpl")
    private IRedis<String, String> template;

    public String setToken(String accessToken) {
        String refreshToken = template.get(appName, accessToken);
        if (refreshToken != null)
            return refreshToken;
        ResultVO<TokenVo> tokenVoResultVO = authenticationCenter.generateRefreshToken(accessToken);
        if (tokenVoResultVO.getResult() == null)
            throw new ResultException(StatusCodeEnum.TOKEN_ERROR.getCode(), StatusCodeEnum.TOKEN_ERROR.getMessage());
        template.set(appName, accessToken, tokenVoResultVO.getResult().getRefreshToken());
        return tokenVoResultVO.getResult().getRefreshToken();
    }

    public void updateRedis(String key, String newKey) {
        String refreshToken = template.get(appName, key);
        template.delete(appName, key);
        template.set(appName, newKey, refreshToken);
    }

}
