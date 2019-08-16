package com.allqj.virtual_number_administrate.util.resultProxy.impl;

import com.allqj.virtual_number_administrate.business.vo.ResultVO;
import com.allqj.virtual_number_administrate.util.proxy.IProxyFunction;
import com.allqj.virtual_number_administrate.util.proxy.impl.AbstractBaseProxy;
import com.allqj.virtual_number_administrate.util.resultProxy.annotation.ResultProxy;
import com.allqj.virtual_number_administrate.util.resultProxy.exception.ResultException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class HttpResultProxy extends AbstractBaseProxy<ResultProxy> {
    @Override
    public Object intercept(ResultProxy annotation, Object bean, Method method, Object[] args, IProxyFunction<Object> run) throws Throwable {
        try {
            Object result = run.run();
            return result;
        } catch (Exception ex) {
            return errorProcessor(ex, ex.getCause());
        }
    }

    //异常处理
    private Object errorProcessor(Exception ex, Throwable subError) throws Throwable {
        if (subError == null)
            throw ex;
        if (subError instanceof ResultException)
            return ResultVO.newInstance(null,
                    ((ResultException) subError).getStatusCode(), ((ResultException) subError).getExceptionMessage());
        return errorProcessor(ex, subError.getCause());
    }
}
