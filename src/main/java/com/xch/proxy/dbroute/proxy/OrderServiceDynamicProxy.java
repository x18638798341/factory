package com.xch.proxy.dbroute.proxy;

import com.xch.proxy.dbroute.IOrderService;
import com.xch.proxy.dbroute.Order;
import com.xch.proxy.dbroute.db.DynamicDataSourceEntity;
import com.xch.proxy.dynamic.custom.CustomInvocationHandler;
import com.xch.proxy.dynamic.custom.utils.ProxyFactory;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceDynamicProxy implements CustomInvocationHandler {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    private static final Method proxyMethod;

    static {
        try {
            proxyMethod = IOrderService.class.getMethod("createOrder", Order.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    private Object target;

    public OrderServiceDynamicProxy(Object target) {
        this.target = target;
    }

    public Object getInstance(){
        return new ProxyFactory(target.getClass().getInterfaces(), this).newProxyInstance();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.equals(proxyMethod)) {
            return method.invoke(target, args);
        }
        before(args);
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("动态代理重置数据源");
        DynamicDataSourceEntity.restore();
    }

    private void before(Object[] args) {
        Order order = (Order) args[0];
        Long createTime = order.getCreateTime();
        Integer year = Integer.parseInt(sdf.format(new Date(createTime)));
        System.out.println("动态代理类自动分配到[DB_" + year + "] 数据源处理数据");
        DynamicDataSourceEntity.set(year);
    }
}
