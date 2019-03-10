package com.xch.singleton.layz;

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
