package com.xch.proxy.dynamic.custom;

import com.xch.proxy.dynamic.custom.utils.ProxyFactory;
import com.xch.proxy.dynamic.jdk.Girl;
import com.xch.proxy.staticproxy.Person;
import org.junit.Test;

public class ProxyFactoryTest {

    @Test
    public void test1(){
        Girl girl = new Girl();
        Object proxyInstance = new ProxyFactory(new Class[]{Person.class}, new CustomMeiPo(girl)).newProxyInstance();
        ((Person) proxyInstance).findLove();
    }
}
