package com.xch.singleton.hungry;

public class HungryStaticSingleton {

    private static final HungryStaticSingleton INSTANCE = new HungryStaticSingleton();

    private HungryStaticSingleton(){}

    public static HungryStaticSingleton getInstance(){
        return INSTANCE;
    }
}
