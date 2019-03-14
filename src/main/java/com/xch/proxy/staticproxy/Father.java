package com.xch.proxy.staticproxy;

public class Father implements Person {
    private Person proxy;

    public Father(Person proxy) {
        this.proxy = proxy;
    }

    @Override
    public void findLove() {
        System.out.println("父亲物色对象");
        proxy.findLove();
        System.out.println("双方同意，确定关系");
    }
}
