package com.xch.factory;

public class MagnesiumMemoryBank implements IMemoryBank {
    @Override
    public void use() {
        System.out.println("欢迎使用镁光内存条");
    }
}
