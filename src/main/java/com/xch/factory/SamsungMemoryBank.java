package com.xch.factory;

public class SamsungMemoryBank implements IMemoryBank {
    @Override
    public void use() {
        System.out.println("欢迎使用三星内存条");
    }
}
