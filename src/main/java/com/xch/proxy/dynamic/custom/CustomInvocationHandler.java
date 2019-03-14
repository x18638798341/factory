package com.xch.proxy.dynamic.custom;

import java.lang.reflect.Method;

public interface CustomInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
