package com.xch.factory.abstr;

import com.xch.factory.IMemoryBank;
import com.xch.factory.MagnesiumMemoryBank;

public class MagnesiumFactory implements IFactory {
    @Override
    public IMemoryBank createMemoryBank() {
        return new MagnesiumMemoryBank();
    }

    @Override
    public IBios createBios() {
        return new MegnesiumBios();
    }
}
