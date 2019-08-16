package com.allqj.virtual_number_administrate.util.resultProxy.annotation;


import com.allqj.virtual_number_administrate.util.proxy.annotations.Proxy;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Proxy
public @interface ResultProxy {
}
