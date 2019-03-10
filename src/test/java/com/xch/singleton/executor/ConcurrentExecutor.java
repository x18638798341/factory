package com.xch.singleton.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConcurrentExecutor {

    public static void execute(int count, final Runnable runnable){
        final Object signal = new Object();
        Executor executor = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executor.execute(new Thread( () -> {
                synchronized (signal){
                    try {
                        signal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runnable.run();
            }));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (signal){
            signal.notifyAll();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
