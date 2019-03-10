package com.xch.singleton.register;

/***
 * 借助枚举的原理 实现单例 ， 可以防止序列化， 反射破坏
 */
public enum EnumSingleton {

    INSTANCE;


    public static EnumSingleton getInstance() {return INSTANCE;}
}
