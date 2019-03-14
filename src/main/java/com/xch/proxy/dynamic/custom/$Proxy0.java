package com.xch.proxy.dynamic.custom;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

import com.xch.proxy.staticproxy.Person;
import com.xch.proxy.dynamic.custom.CustomProxy;
import com.xch.proxy.dynamic.custom.CustomInvocationHandler;

public final class $Proxy0 extends CustomProxy implements Person {
    protected $Proxy0(CustomInvocationHandler h) {
        super(h);
    }

    private static Method m1;

    private static Method m2;

    private static Method m3;

    private static Method m4;

    static {
        try {
            Class c1 = Object.class;
            m1 = c1.getMethod("hashCode", new Class[]{});
            m2 = c1.getMethod("equals", new Class[]{Object.class});
            m3 = c1.getMethod("toString", new Class[]{});
            Class c2 = Person.class;
            m4 = c2.getMethod("findLove", new Class[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int hashCode() {
        try {
            Object[] args = new Object[]{};
            return (int) h.invoke(this, m1, args);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public boolean equals(Object arg0) {
        try {
            Object[] args = new Object[]{arg0};
            return (boolean) h.invoke(this, m2, args);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public String toString() {
        try {
            Object[] args = new Object[]{};
            return (String) h.invoke(this, m3, args);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public void findLove() {
        try {
            Object[] args = new Object[]{};
            h.invoke(this, m4, args);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
