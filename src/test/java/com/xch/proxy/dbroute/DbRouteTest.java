package com.xch.proxy.dbroute;

import com.xch.proxy.dbroute.proxy.OrderServiceDynamicProxy;
import com.xch.proxy.dbroute.proxy.OrderServiceStaticProxy;
import org.junit.Test;

import java.util.Date;

public class DbRouteTest {

    @Test
    public void test1(){
        OrderDao dao = new OrderDao();
        IOrderService service = new OrderService(dao);
        var proxy = new OrderServiceStaticProxy(service);
        Order order = new Order();
        order.setCreateTime(new Date().getTime());
        proxy.createOrder(order);
    }

    @Test
    public void test2(){
        OrderDao dao = new OrderDao();
        IOrderService service = new OrderService(dao);
        var proxy = new OrderServiceDynamicProxy(service);
        IOrderService instance = (IOrderService) proxy.getInstance();
        Order order = new Order();
        order.setCreateTime(new Date().getTime());
        instance.createOrder(order);
    }
}
