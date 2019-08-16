package com.allqj.virtual_number_administrate.util.distributedLocks.annotations;


import com.allqj.virtual_number_administrate.util.proxy.annotations.Proxy;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Proxy
public @interface UseDistributedLocks {

}
