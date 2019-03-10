package com.xch.singleton.hungry;

/***
 * 不存在并发上的多实例问题， 在类加载的时候就实例化， 会出现没有使用的情况， 浪费资源
 */
public class HungrySingleton {

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return INSTANCE;
    }
}
