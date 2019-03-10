package com.xch.singleton;

import com.xch.singleton.executor.ConcurrentExecutor;
import com.xch.singleton.layz.LazyInnerClassSingleton;
import com.xch.singleton.layz.SimpleLazySingleton;
import org.junit.Test;

public class LazyLoadTest {


    @Test
    public void test1(){
        ConcurrentExecutor.execute(10, () -> System.out.println(Thread.currentThread().getName() + " acquire singleton : " + SimpleLazySingleton.getInstance()));
    }

    @Test
    public void test2(){
        Runnable runnable = () ->{
            LazyInnerClassSingleton instance = LazyInnerClassSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " acquire singleton : " + instance);
        };
        Thread t1 = new Thread(runnable, "thread 1");
        Thread t2 = new Thread(runnable, "thread 2");
        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
