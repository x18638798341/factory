package com.xch.proxy.staticproxy;

import org.junit.Test;

public class StaticProxyTest {

    @Test
    public void test1(){
        Person son = new Son();
        Person father = new Father(son);
        father.findLove();
    }
}
