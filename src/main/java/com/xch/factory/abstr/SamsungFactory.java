package com.xch.factory.abstr;

import com.xch.factory.IMemoryBank;
import com.xch.factory.SamsungMemoryBank;

public class SamsungFactory implements IFactory {
    @Override
    public IMemoryBank createMemoryBank() {
        return new SamsungMemoryBank();
    }

    @Override
    public IBios createBios() {
        return new SamsungBios();
    }
}
