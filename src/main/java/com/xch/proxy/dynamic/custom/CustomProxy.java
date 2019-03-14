package com.xch.proxy.dynamic.custom;
public class CustomProxy {

    protected CustomInvocationHandler h;

    public CustomProxy(CustomInvocationHandler h) {
        this.h = h;
    }

}
