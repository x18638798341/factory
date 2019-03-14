package com.xch.proxy.dynamic.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBMeiPo implements MethodInterceptor {


    public Object getInstance(Class<?> cls){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是CGlib媒婆， 我现在确认你的要求");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("寻找结束");
        return result;
    }
}
