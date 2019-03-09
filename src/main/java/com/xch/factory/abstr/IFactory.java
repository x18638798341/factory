package com.xch.factory.abstr;

import com.xch.factory.IMemoryBank;

public interface IFactory {

    IMemoryBank createMemoryBank();

    IBios createBios();
}
