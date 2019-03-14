package com.xch.proxy.dynamic.jdk;

import com.xch.proxy.staticproxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKMeipo implements InvocationHandler {

    private Person target;

    public JDKMeipo(Person person) {
        this.target = person;
    }

    public Object getInstance(){
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(JDKMeipo.class.getClassLoader(), interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆， 我现在确认你的要求");
        Object result = method.invoke(target, args);
        System.out.println("寻找结束");
        return result;
    }
}
