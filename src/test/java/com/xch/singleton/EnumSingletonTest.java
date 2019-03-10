package com.xch.singleton;

import com.xch.singleton.executor.ConcurrentExecutor;
import com.xch.singleton.register.EnumSingleton;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class EnumSingletonTest {

    @Test
    public void test1(){
        Runnable runnable = () ->{
            EnumSingleton instance = EnumSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " acquire singleton : " + instance);
        };
        ConcurrentExecutor.execute(10, runnable);
//        Thread t1 = new Thread(runnable, "thread 1");
//        Thread t2 = new Thread(runnable, "thread 2");
//        t1.start();
//        t2.start();
//
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<EnumSingleton> singletonClass = EnumSingleton.class;
        Constructor<EnumSingleton> constructor = singletonClass.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance("xch", 0));
    }
}
