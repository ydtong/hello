package com.allqj.virtual_number_administrate.util.tokenVerification.aop;


import com.allqj.virtual_number_administrate.business.enums.StatusCodeEnum;
import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import com.allqj.virtual_number_administrate.util.tokenVerification.aop.annotation.Token;
import com.allqj.virtual_number_administrate.util.tokenVerification.base.authenticationCenter.IAuthenticationCenter;
import com.allqj.virtual_number_administrate.util.tokenVerification.base.vo.TokenVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/*@Aspect
@Component*/
public class TokenVerificationAop {
    @Autowired
    private VerificationToken verificationToken;
    @Resource
    private IAuthenticationCenter authenticationCenter;
    @Autowired
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    @Pointcut("@annotation(com.allqj.virtual_number_administrate.util.tokenVerification.aop.annotation.Token)")
    private void lockPoint() {

    }

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Token token = method.getAnnotation(Token.class);
        //不添加注解直接调用方法
        if (token == null)
            return joinPoint.proceed();
        String accessToken = request.getHeader("token");
        if (accessToken == null)
            throw new ResultException(StatusCodeEnum.GO_LOG_IN.getCode(), StatusCodeEnum.GO_LOG_IN.getMessage());
        // 验证token是否有效
        ResultVO<Boolean> verification = authenticationCenter.verification(accessToken);
        //判断生成刷新token
        String refreshToken = verificationToken.setToken(accessToken);
        if (refreshToken == null)
            throw new ResultException(StatusCodeEnum.TOKEN_ERROR.getCode(), StatusCodeEnum.TOKEN_ERROR.getMessage());
        //token有效直接调用方法
        if (verification.getResult() != null) {
            response.setHeader("Pragma", accessToken);
            return joinPoint.proceed();
        }
        //根据刷新token获取新的访问token
        ResultVO<TokenVo> tokenVo = authenticationCenter.generateNewAccessToken(refreshToken);
        if (tokenVo.getResult() == null)
            throw new ResultException(StatusCodeEnum.TOKEN_ERROR.getCode(), StatusCodeEnum.TOKEN_ERROR.getMessage());
        // 更新緩存
        verificationToken.updateRedis(accessToken, tokenVo.getResult().getRefreshToken());
        response.setHeader("Pragma", tokenVo.getResult().getAccessToken());
        return joinPoint.proceed();
    }
}
