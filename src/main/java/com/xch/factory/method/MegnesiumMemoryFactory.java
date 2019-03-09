package com.xch.factory.method;

import com.xch.factory.IMemoryBank;
import com.xch.factory.MagnesiumMemoryBank;
import com.xch.factory.SamsungMemoryBank;

public class MegnesiumMemoryFactory implements IMemoryBankFactory {
    @Override
    public IMemoryBank create() {
        return new MagnesiumMemoryBank();
    }
}
