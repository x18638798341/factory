package com.xch.proxy.dynamic.jdk;

import com.xch.proxy.staticproxy.Person;

public class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅， 身高180， 6块腹肌");
    }
}
