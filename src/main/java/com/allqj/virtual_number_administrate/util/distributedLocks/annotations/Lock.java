package com.allqj.virtual_number_administrate.util.distributedLocks.annotations;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {
    String key() default "";
}
