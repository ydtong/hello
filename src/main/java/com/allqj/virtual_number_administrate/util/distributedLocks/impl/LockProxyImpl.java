package com.allqj.virtual_number_administrate.util.distributedLocks.impl;

import com.allqj.virtual_number_administrate.util.distributedLocks.IRedissonService;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.Lock;
import com.allqj.virtual_number_administrate.util.distributedLocks.annotations.UseDistributedLocks;
import com.allqj.virtual_number_administrate.util.proxy.impl.AbstractBaseProxy;
import com.allqj.virtual_number_administrate.util.proxy.IProxyFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;


/**
 * 分布式锁的代理
 */
//@Service
public class LockProxyImpl extends AbstractBaseProxy<UseDistributedLocks> {

    @Autowired
    private IRedissonService redissonService;

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
    @Override
    public Object intercept(UseDistributedLocks annotation, Object bean, Method method, Object[] args, IProxyFunction<Object> run) throws Throwable {

        //如果当前方法没有锁
        Lock lock = method.getAnnotation(Lock.class);
        if (lock == null)
            return run.run();

        Object result = null;

        //开启锁，执行方法，然后关闭锁
        String key = getLockKey(lock, bean, method);
        try {
            redissonService.lock(key);
            return run.run();
        } finally {
            redissonService.free(key);
        }
    }

    //获取锁的key，如果没有指定，就用类的完整包名加方法名
    private String getLockKey(Lock lock, Object bean, Method method) {
        if (lock.key().isEmpty())
            return bean.getClass().getName() + "." + method.getName();
        return lock.key();
    }
}
