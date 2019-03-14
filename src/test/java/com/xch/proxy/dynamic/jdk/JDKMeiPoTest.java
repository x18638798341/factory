package com.xch.proxy.dynamic.jdk;

import com.xch.proxy.staticproxy.Person;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class JDKMeiPoTest {

    @Test
    public void test1(){
        Person girl = (Person) new JDKMeipo(new Girl()).getInstance();
        girl.findLove();
    }

    @Test
    public void test2() throws Exception {
        Person girl = (Person) new JDKMeipo(new Girl()).getInstance();
        String simpleName = girl.getClass().getSimpleName();
        Class<?> proxyGenerator = Class.forName("java.lang.reflect.ProxyGenerator");
        Class<?>[] classes = {String.class, Class[].class};
        Method generateProxyClass = proxyGenerator.getDeclaredMethod("generateProxyClass", classes);
        generateProxyClass.setAccessible(true);
        byte[] buffer = (byte[]) generateProxyClass.invoke(null, simpleName , new Class[]{Person.class});
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        Path path= Paths.get("E:\\project\\Java\\pattern\\src\\main\\resources", simpleName + ".class");
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
    }
}
