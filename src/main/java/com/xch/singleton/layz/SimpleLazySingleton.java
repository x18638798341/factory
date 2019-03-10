package com.xch.singleton.layz;

/***
 * 简单的懒加载方法， 在并发的情况下可能会产生多次构建实例的情况
 */
public class SimpleLazySingleton {

    private static SimpleLazySingleton INSTANCE;

    private SimpleLazySingleton(){}

    public static SimpleLazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleLazySingleton();
        }
        return INSTANCE;
    }

}
