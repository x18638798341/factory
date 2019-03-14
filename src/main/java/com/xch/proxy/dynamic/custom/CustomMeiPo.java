package com.xch.proxy.dynamic.custom;

import com.xch.proxy.dynamic.custom.utils.ProxyFactory;
import com.xch.proxy.staticproxy.Person;

import java.lang.reflect.Method;

public class CustomMeiPo implements CustomInvocationHandler{

    private Person target;

    public CustomMeiPo(Person person) {
        this.target = person;
    }

    public Object getInstance(){
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return new ProxyFactory(interfaces, this).newProxyInstance();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是Custom媒婆， 我现在确认你的要求");
        Object result = method.invoke(target, args);
        System.out.println("寻找结束");
        return result;
    }
}
