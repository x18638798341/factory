package com.xch.factory.simple;

import com.xch.factory.IMemoryBank;
import com.xch.factory.MagnesiumMemoryBank;
import com.xch.factory.SamsungMemoryBank;

public class MemoryBankFactory {

    public IMemoryBank create(String type){
        if ("samsung".equals(type)) {
            return new SamsungMemoryBank();
        } if ("magnesium".equals(type)) {
            return new MagnesiumMemoryBank();
        }else {
            return null;
        }
    }
}
