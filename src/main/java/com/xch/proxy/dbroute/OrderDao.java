package com.xch.proxy.dbroute;

public class OrderDao {

    public int insert(Order order){
        System.out.println("OrderDao创建order成功 order -> " + order);
        return 1;
    }
}
