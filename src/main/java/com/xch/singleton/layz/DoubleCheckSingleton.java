package com.xch.singleton.layz;

/***
 * 两次检查来保证线程安全
 */
public class DoubleCheckSingleton {

    private static DoubleCheckSingleton INSTANCE;

    private DoubleCheckSingleton(){}

    public static DoubleCheckSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckSingleton.class){
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
