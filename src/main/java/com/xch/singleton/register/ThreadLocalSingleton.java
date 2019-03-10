package com.xch.singleton.register;

/***
 * 通过threadLocal 创建单线程单例， 避免并发问题
 */
public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> SINGLETON_THREAD_LOCAL =
            ThreadLocal.withInitial(ThreadLocalSingleton::new);
    private ThreadLocalSingleton (){}

    public static ThreadLocalSingleton getInstance(){
        return SINGLETON_THREAD_LOCAL.get();
    }
}
