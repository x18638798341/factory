package com.xch.reflect;

import com.xch.proxy.dynamic.custom.utils.ProxyFactory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionTest {

    @Test
    public void test1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> type = Class.forName("sun.misc.Unsafe");
        Method getUnsafe = type.getMethod("getUnsafe");
        getUnsafe.setAccessible(true);
        Object unsafe =  getUnsafe.invoke(null);
        System.out.println(unsafe);
    }

    @Test
    public void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> type = Class.forName("sun.misc.Unsafe");
        Constructor<?> constructor = type.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(new Object[0]);
        Assert.assertNotNull(o);
    }

    @Test
    public void test3() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> type = Class.forName("sun.misc.Unsafe");
        Field theUnsafe = type.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Object o = theUnsafe.get(null);
        Assert.assertNotNull(o);
    }

    @Test
    public void test4(){
        Class<Demo1> personClass = Demo1.class;
        for (Method method : personClass.getMethods()) {
            System.out.println(method);
            System.out.println(Modifier.toString(method.getModifiers()));
            System.out.println(method.getReturnType().getName());
            Arrays.stream(method.getParameterTypes()).map(Class::getName).forEach(System.out::println);
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getType().getName());
                System.out.println(parameter.getName());
            }
        }
    }

    @Test
    public void test5(){
        StringBuilder sb = new StringBuilder("xx");
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    @Test
    public void test6(){
        System.out.println(ReflectionTest.class.getResource("").getPath());
    }

    @Test
    public void test7(){
        System.out.println(ReflectionTest.class.getResource("/").getPath());

    }



}
