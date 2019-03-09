package com.xch.factory.method;

import com.xch.factory.IMemoryBank;

public class FactoryMethodMain {

    public static void main(String[] args) {
        IMemoryBankFactory factory = new SamsungMemoryFactory();
        IMemoryBank memoryBank = factory.create();
        memoryBank.use();
    }
}
