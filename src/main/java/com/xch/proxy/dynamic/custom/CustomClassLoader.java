package com.xch.proxy.dynamic.custom;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CustomClassLoader extends ClassLoader {
    public static final CustomClassLoader INSTANCE;

    private static final String classPath;

    static {
        INSTANCE = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
        classPath = CustomClassLoader.class.getResource("/").getPath();
    }
    private CustomClassLoader(ClassLoader parent){
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String className = CustomClassLoader.class.getPackageName() + "." + name;
        String filePath = className.replace(".", "/") + ".class";
        File file = new File(classPath + filePath);
        if (file.exists()) {
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                return defineClass(className, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.loadClass(name);
    }
}
