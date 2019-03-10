package com.xch.singleton.layz;

/***
 * 由jvm层面实现线程单例, 单例安全， 不能存在并发多实例的情况
 */
public class LazyInnerClassSingleton {


    private LazyInnerClassSingleton() {
        if (Holder.singleton != null) {
            throw new RuntimeException("单例状态被破坏");
        }
    }

    public static LazyInnerClassSingleton getInstance(){
        return Holder.singleton;
    }

    private static class Holder{
        private static LazyInnerClassSingleton singleton = new LazyInnerClassSingleton();
    }
}
