package com.allqj.virtual_number_administrate.util.log.annotations;


import com.allqj.virtual_number_administrate.util.proxy.annotations.Proxy;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Proxy
public @interface AddLog {

}
