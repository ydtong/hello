package com.allqj.virtual_number_administrate.util.log.impl;

import com.allqj.virtual_number_administrate.util.dateOperation.DateOperation;
import com.allqj.virtual_number_administrate.util.jsonOperation.JsonOperation;
import com.allqj.virtual_number_administrate.util.log.annotations.AddLog;
import com.allqj.virtual_number_administrate.util.log.annotations.LogDescribe;
import com.allqj.virtual_number_administrate.util.proxy.IProxyFunction;
import com.allqj.virtual_number_administrate.util.proxy.impl.AbstractBaseProxy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Service
public class LogProxy extends AbstractBaseProxy<AddLog> {

    @Override
    public Object intercept(AddLog annotation, Object bean, Method method, Object[] args, IProxyFunction<Object> run) throws Throwable {
        Object result = null;
        try {
            result = run.run();
            return result;
        } finally {
            print(method, args, result);
        }
    }

    //方法名
    private String methodNameInfo(Method method) {
        return method.getDeclaringClass().getName() + "." + method.getName();
    }

    //描述
    private String describeInfo(LogDescribe logDescribe) {
        if (logDescribe != null)
            return logDescribe.value();
        return null;
    }

    //参数
    private String argsInfo(Method method, Object[] values) {
        String methodParameter = "";
        Parameter[] parameters = method.getParameters();
        //遍历参数
        for (int i = 0; i < values.length; i++)
            methodParameter = methodParameter + parameters[i].getName() + "=" + JsonOperation.toJson(values[i]) + ";";
        return methodParameter;
    }

    //返回结果
    private String resultInfo(Object result) {
        return JsonOperation.toJson(result);
    }

    //打印
    private void print(Method method, Object[] values, Object result) {
        LogDescribe logDescribe = method.getAnnotation(LogDescribe.class);
        if (logDescribe == null)
            return;

        String tag = "|log| ";
        String methodNameInfo = tag + "方法名：" + methodNameInfo(method) + "\n";
        String describeInfo = tag + "描述：" + describeInfo(logDescribe) + "\n";
        String argsInfo = tag + "参数：" + argsInfo(method, values) + "\n";
        String resultInfo = tag + "返回结果：" + resultInfo(result) + "\n";
        String time = tag + "时间：" + DateOperation.newDateString("yyyy-MM-dd HH:mm:ss") + "\n";
        String log = "\n" + methodNameInfo + describeInfo + argsInfo + resultInfo + time;
        System.out.println(log);
    }
}
