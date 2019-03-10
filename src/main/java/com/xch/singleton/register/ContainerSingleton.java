package com.xch.singleton.register;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {

    private static final Map<String, Object> ioc = new ConcurrentHashMap<>();

    private ContainerSingleton() {
    }

    public static Object getBean(String className){
        synchronized (ioc){
            if (ioc.containsKey(className)) {
                return ioc.get(className);
            } else {
                Object o = null;
                try {
                    o = Class.forName(className).getDeclaredConstructor().newInstance();
                    ioc.put(className, o);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return o;
            }
        }
    }

}
