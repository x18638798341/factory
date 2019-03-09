package com.xch.factory.method;

import com.xch.factory.IMemoryBank;

public abstract class AbstractMemoryBankFactory implements IMemoryBankFactory {


    protected abstract void beforeCreate();

    protected abstract IMemoryBank doCreate();

    protected abstract void afterCreate();

    @Override
    public IMemoryBank create() {
        beforeCreate();
        IMemoryBank bank = doCreate();
        afterCreate();
        return bank;
    }
}
