package com.allqj.virtual_number_administrate.util.proxy.impl;


import com.allqj.virtual_number_administrate.util.proxy.IProxyClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;


/**
 * 抽象的基本代理类
 *
 * @param <A> 自定义的代理注解
 */
public abstract class AbstractBaseProxy<A extends Annotation> implements IProxyClass<A> {

    //代理注解类型
    private Class<A> annotationClass;

    /**
     * 初始化获取代理注解类型
     */
    public AbstractBaseProxy() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        annotationClass = (Class<A>) type.getActualTypeArguments()[0];
    }

    //获取代理注解类型
    public Class<A> getAnnotationType() {
        return annotationClass;
    }
}
