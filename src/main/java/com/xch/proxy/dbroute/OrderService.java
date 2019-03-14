package com.xch.proxy.dbroute;

public class OrderService implements IOrderService {

    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService调用OrderDao创建Order");
        orderDao.insert(order);
        return 1;
    }
}
