package com.xch.factory.simple;

import com.xch.factory.IMemoryBank;

public class SImpleMain {


    public static void main(String[] args) {
        MemoryBankFactory memoryBankFactory = new MemoryBankFactory();

        IMemoryBank memoryBank = memoryBankFactory.create("samsung");

        memoryBank.use();
    }
}
