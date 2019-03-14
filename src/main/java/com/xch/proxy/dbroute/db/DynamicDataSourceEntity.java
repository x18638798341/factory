package com.xch.proxy.dbroute.db;

public class DynamicDataSourceEntity {

    public static final String DEFALULT_SOURCE = null;

    private final static ThreadLocal<String> LOCAL = new ThreadLocal<>();

    public static String get(){
        return LOCAL.get();
    }

    public static void restore(){
        LOCAL.set(DEFALULT_SOURCE);
    }

    public static void set(String source){
        LOCAL.set(source);
    }

    public static void set (int year) {
        LOCAL.set("DB_" + year);
    }


}
