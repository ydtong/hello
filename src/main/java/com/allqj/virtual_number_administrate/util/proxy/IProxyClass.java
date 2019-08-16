package com.allqj.virtual_number_administrate.util.proxy;


import java.lang.reflect.Method;


/**
 * 代理类
 *
 * @param <A> 自定义的代理注解
 */
public interface IProxyClass<A> {
    /**
     * 获取代理注解类型
     *
     * @return
     */
    Class<A> getAnnotationType();

    /**
     * 拦截方法
     *
     * @param annotation 自定义注解的实例
     * @param bean       当前类的bean
     * @param method     当前方法
     * @param args       方法参数
     * @param run        代理函数式（用该参数执行代理方法获取返回值，不要用method.invoke()，如果是多个代理的情况，就不会进入下一个代理）
     * @return
     * @throws Throwable
     */
    Object intercept(A annotation, Object bean, Method method, Object[] args, IProxyFunction<Object> run) throws Throwable;

}
