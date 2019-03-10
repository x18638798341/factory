package com.xch.singleton.seria;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {

    private static SerializableSingleton INSTANCE = new SerializableSingleton();

    private SerializableSingleton() {
    }

    public static SerializableSingleton getInstance(){
        return INSTANCE;
    }

    /***
     * 虽然这里确保了返回值的单一性， 但是jvm底层还是多次实例化该类
     * @return 单例对象
     */
    public Object readResolve(){
        return INSTANCE;
    }

}
