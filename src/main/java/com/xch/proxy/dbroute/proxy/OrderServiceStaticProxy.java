package com.xch.proxy.dbroute.proxy;

import com.xch.proxy.dbroute.IOrderService;
import com.xch.proxy.dbroute.Order;
import com.xch.proxy.dbroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderServiceStaticProxy implements IOrderService {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    private IOrderService orderService;

    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {
        Long createTime = order.getCreateTime();
        Integer year = Integer.parseInt(sdf.format(new Date(createTime)));
        System.out.println("静态代理类自动分配到[DB_" + year + "] 数据源处理数据");
        DynamicDataSourceEntity.set(year);
        var result = this.orderService.createOrder(order);
        DynamicDataSourceEntity.restore();
        return result;
    }
}
