package com.xch.proxy.dynamic.cglib;

import org.junit.Test;
import org.springframework.cglib.core.DebuggingClassWriter;

public class CGlibTest {

    @Test
    public void test1(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\project\\Java\\pattern\\src\\main\\resources\\cglib_proxy_class");
        Customer instance = (Customer) new CGLIBMeiPo().getInstance(Customer.class);
        instance.findLove();
    }
}
