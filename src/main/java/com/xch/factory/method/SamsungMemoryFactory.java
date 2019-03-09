package com.xch.factory.method;

import com.xch.factory.IMemoryBank;
import com.xch.factory.SamsungMemoryBank;

public class SamsungMemoryFactory implements IMemoryBankFactory {
    @Override
    public IMemoryBank create() {
        return new SamsungMemoryBank();
    }
}
