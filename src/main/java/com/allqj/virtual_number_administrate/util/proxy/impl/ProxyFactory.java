package com.allqj.virtual_number_administrate.util.proxy.impl;

import com.allqj.virtual_number_administrate.util.proxy.IProxyClass;
import com.allqj.virtual_number_administrate.util.proxy.IProxyFunction;
import org.springframework.beans.BeansException;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


/**
 * 代理工厂
 */
class ProxyFactory implements MethodInterceptor {

    private Map<Class<? extends Annotation>, IProxyClass<Annotation>> proxyMap;
    private List<Annotation> annotationList;
    private Object bean;
    private Class<?> beanClass;

    public ProxyFactory(Map<Class<? extends Annotation>, IProxyClass<Annotation>> proxyMap, List<Annotation> annotationList, Object bean, Class<?> beanClass) throws BeansException {
        this.proxyMap = proxyMap;
        this.annotationList = annotationList;
        this.bean = bean;
        this.beanClass = beanClass;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if (proxyMap == null || proxyMap.size() < 1 || annotationList == null || annotationList.size() < 1)
            return method.invoke(bean, objects);

        //如果是父类的方法，不走代理
        if (method.getDeclaringClass() != beanClass)
            return method.invoke(bean, objects);

        //循环执行所有代理
        IProxyFunction<Object> run = () -> method.invoke(bean, objects);
        for (int i = 0; i < annotationList.size(); i++) {
            int index = i;
            final IProxyFunction<Object> proxyFunction = run;
            IProxyClass<Annotation> proxyClass = proxyMap.get(annotationList.get(index).annotationType());
            if (proxyClass != null)
                run = () -> proxyClass.intercept(annotationList.get(index), bean, method, objects, proxyFunction);
        }
        return run.run();
    }
}
