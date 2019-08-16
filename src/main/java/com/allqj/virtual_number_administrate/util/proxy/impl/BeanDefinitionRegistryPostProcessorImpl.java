package com.allqj.virtual_number_administrate.util.proxy.impl;

import com.allqj.virtual_number_administrate.business.service.impl.TestServiceImpl;
import com.allqj.virtual_number_administrate.util.proxy.IProxyClass;
import com.allqj.virtual_number_administrate.util.proxy.annotations.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * bean处理器
 */
@Service
public class BeanDefinitionRegistryPostProcessorImpl implements BeanPostProcessor {

    //所有自定义代理的实现
    private Map<Class<? extends Annotation>, IProxyClass<Annotation>> proxyMap = new HashMap<>();

    private Map<String, List<Annotation>> proxyAnnotationMap = new HashMap<>();
    private Map<String, Object> proxyBeanMap = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof TestServiceImpl) {
            System.out.println("");
        }

        List<Annotation> annotationList = getAnnotationList(bean.getClass());
        if (annotationList != null && annotationList.size() > 0) {
            proxyAnnotationMap.put(beanName, annotationList);
            proxyBeanMap.put(beanName, bean);
        }
        //将自定义代理添加到map
        if (bean instanceof IProxyClass)
            addProxyMap((IProxyClass) bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestServiceImpl) {
            System.out.println("");
        }
        Object obj = proxyBeanMap.get(beanName);
        if (obj != null)
            return createProxyFactory(proxyAnnotationMap.get(beanName), obj.getClass(), bean);
        return bean;
    }

    //将自定义代理添加到map集合中
    private void addProxyMap(IProxyClass proxyClass) {
        Class annotationClass = proxyClass.getAnnotationType();
        if (proxyAnnotation(annotationClass) == null)
            throw new BeanCreationException(annotationClass.getName() + "没有找到" + Proxy.class.getName());
        proxyMap.put(annotationClass, proxyClass);
    }

    //将bean放入代理工厂，返回一个代理工厂
    private Object createProxyFactory(List<Annotation> annotationList, Class<?> beanClass, Object bean) throws BeansException {

        if (annotationList == null || annotationList.size() < 1)
            return bean;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new ProxyFactory(proxyMap, annotationList, bean, beanClass));
        return enhancer.create();
    }

    //获取被代理类上所有的自定义代理注解
    private List<Annotation> getAnnotationList(Class<?> type) {
        List<Annotation> annotationList = new ArrayList<>();
        for (Annotation annotation : type.getAnnotations()) {
            if (proxyAnnotation(annotation.annotationType()) != null)
                annotationList.add(annotation);
        }
        return annotationList;
    }

    //查找注解中有没有指定的注解
    private Proxy proxyAnnotation(Class<? extends Annotation> annotationClass) {
        return annotationClass.getAnnotation(Proxy.class);
    }
}
