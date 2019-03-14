package com.xch.proxy.dynamic.custom.utils;

import com.xch.proxy.dynamic.custom.CustomClassLoader;
import com.xch.proxy.dynamic.custom.CustomInvocationHandler;
import com.xch.proxy.dynamic.custom.CustomProxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProxyFactory {

    public static final String ln ="\n";

    private static AtomicLong proxy_count = new AtomicLong();

    /***
     * 需要加入的包
     */
    private Map<Class<?>, String> packages = new HashMap<>();

    /***
     * 包含的接口
     */
    private Class<?>[] interfaces;

    private ClassLoader classLoader = CustomClassLoader.INSTANCE;

    /***
     * 父类
     */
    private Class<?> superCls = CustomProxy.class;

    private String[] staticMethodFields;
    /***
     * 生成的方法
     */
    private String[] methods;


    private Map<Class<?>, List<Method>> typeMethodsMapping;

    private static final Class<?>[] defautl_packages;

    private String staticBlock;

    private static final Method[] default_methods;
    static {
        defautl_packages = new Class[]{
                CustomProxy.class,
                Method.class,
                UndeclaredThrowableException.class,
                CustomInvocationHandler.class,
        };
        Class<Object> objectClass = Object.class;
        Method[] methods1 = null;
        try {
            methods1 = new Method[]{
                    objectClass.getMethod("hashCode"),
                    objectClass.getMethod("equals", Object.class),
                    objectClass.getMethod("toString"),
            };
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            default_methods = methods1;
        }
    }

    {
        for (Class<?> defautl_package : defautl_packages) {
            AddOnePackage(defautl_package);
        }

        AddOnePackage(superCls);
    }

    private CustomInvocationHandler h;

    public ProxyFactory (Class[] interfaces, CustomInvocationHandler h) {
        this.interfaces = interfaces;
        Map<? extends Class<?>, List<Method>> collect = Arrays.stream(interfaces)
                .flatMap(i -> Arrays.stream(i.getMethods()))
                .collect(Collectors.groupingBy(Method::getDeclaringClass, Collectors.toList()));
        typeMethodsMapping = new HashMap<>();
        typeMethodsMapping.put(Object.class, List.of(default_methods));
        typeMethodsMapping.putAll(collect);
        this.h   = h;
    }

    private void AddOnePackage(Class<?> cls){
        if (Pattern.matches("^java\\.lang\\.[A-Z].*$", cls.getName())){
            return;
        }
        if (!packages.containsKey(cls)) {
            packages.put(cls, "import " + cls.getName() + ";");
        }
    }

    public Object newProxyInstance() {
        if (interfaces.length < 1) throw new RuntimeException("没有接口可以代理");
        Arrays.stream(interfaces).forEach(this::AddOnePackage);
        String[] interfacesName = Arrays.stream(interfaces).map(Class::getSimpleName).toArray(i -> new String[i]);
        String pageName = superCls.getPackageName() + ";";

        //class scope
        StringBuilder classScope = new StringBuilder();
        String className = "$Proxy" + proxy_count.getAndIncrement();
        classScope.append("public final class ").append(className)
                .append(" extends ").append(superCls.getSimpleName()).append(" implements ")
                .append(String.join(", ", interfacesName)).append(" {\n");

        classScope.append( generateConstruct(className));
        //static block, field, proxy method
        staticBlock = generateStaticBlock(interfaces);

        classScope.append(String.join(ln, staticMethodFields))
                .append(String.join(ln, staticBlock))
                .append(String.join(ln, methods));
        classScope.append("}");
        //package scope
        classScope.insert(0,  String.join("\n", packages.values()) + ln);
        classScope.insert(0, "package " + pageName + ln);
        String source = classScope.toString();
        String pathFile = CustomProxy.class.getResource("").getPath();
        Path path = Paths.get(pathFile.substring(1), className + ".java");
        try {
            Files.copy(new ByteArrayInputStream(source.getBytes()), path, StandardCopyOption.REPLACE_EXISTING);
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(path.toFile());
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, javaFileObjects);
            task.call();
            fileManager.close();

            Class<?> proxyCls = classLoader.loadClass(className);
            Constructor<?> constructor = proxyCls.getDeclaredConstructor(CustomInvocationHandler.class);
            constructor.setAccessible(true);
            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateConstruct(String className) {
        StringBuilder builder = new StringBuilder();
        builder.append("protected ").append(className)
                .append("(").append("CustomInvocationHandler h) { \n super(h);\n}\n");
        return builder.toString();

    }


    /**
     * 生成静态块
     * 生成静态字段
     * 生成代理方法
     * @param interfaces 该类实现的接口
     * @return
     */
    String generateStaticBlock(Class[] interfaces){
        //生成的静态字段
        List<String> staticField = new ArrayList<>();
        //生成方法代理
        List<String> methodProxy = new ArrayList<>();
        int methodCnt = 1;
        int classCnt = 1;
        StringBuilder staticBlock = new StringBuilder();
        staticBlock.append("static { \n").append("try {");

        for (Class<?> cls : typeMethodsMapping.keySet()) {
            String classArgName = "c" + classCnt;
            //获得cls
            staticBlock.append("Class ").append(classArgName)
                    .append(" = ")
                    .append(cls.getSimpleName())
                    .append(".class; \n");
            for (Method method : typeMethodsMapping.get(cls)) {
                //生成static field
                String methodArgName = "m" + methodCnt;
                staticField.add("private static Method " + methodArgName + ";\n");
                List<String> argTypes = Arrays.stream(method.getParameterTypes())
                        .map(Class::getSimpleName).map(i -> i + ".class")
                        .collect(Collectors.toList());
                //获取 method
                Arrays.stream(method.getParameterTypes()).forEach(this::AddOnePackage);
                staticBlock.append(methodArgName).append(" = ")
                        .append(classArgName).append(".getMethod(\"")
                        .append(method.getName()).append("\", new Class[]{")
                        .append(String.join(", ", argTypes)).append("});");
                //生成 method 方法
                List<String> typeNamePair = Arrays.stream(method.getParameters())
                        .map(m -> m.getType().getSimpleName() + " " + m.getName())
                        .collect(Collectors.toList());
                List<String> argNames = Arrays.stream(method.getParameters())
                        .peek(i-> AddOnePackage(i.getType()))
                        .map(i -> i.getName())
                        .collect(Collectors.toList());
                StringBuilder methodBlock = new StringBuilder();
                methodBlock.append("public ").append(method.getReturnType().getSimpleName())
                        .append(" ").append(method.getName()).append("(")
                        .append(String.join(", ", typeNamePair))
                        .append(") {").append("try {\n").append("Object[] args = new Object[] {")
                        .append(String.join(", ", argNames)).append("};");
                if (!method.getReturnType().equals(void.class)){
                    methodBlock.append("return (").append(method.getReturnType().getSimpleName()).append(") ");
                }
                methodBlock.append("h.invoke(this, ").append(methodArgName).append(", args);")
                        .append("} catch (Throwable e) { throw new UndeclaredThrowableException(e);}}");
                methodProxy.add(methodBlock.toString());
                methodCnt++;
            }
            classCnt ++;
        }
        staticBlock.append("} catch (Exception e) {\n e.printStackTrace(); \n } \n}");
        methods = methodProxy.toArray(new String[0]);
        staticMethodFields = staticField.toArray(new String[0]);
        return staticBlock.toString();
    }

}
