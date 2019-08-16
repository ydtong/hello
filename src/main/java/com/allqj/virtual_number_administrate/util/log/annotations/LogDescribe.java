package com.allqj.virtual_number_administrate.util.log.annotations;


import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDescribe {
    String value();
}
