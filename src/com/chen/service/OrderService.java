package com.chen.service;

import com.chen.bean.Cart;
import com.chen.bean.Order;
import com.chen.bean.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface OrderService {
    /**
     * 结账操作
     * @param cart
     * @return
     */
    public String checkout(Cart cart, User user);

    /**
     * 修改订单状态
     * @param orderid
     * @param status
     */
    public void updateStatus(String orderid, String status);

    /**
     * 获取所有的订单，管理员使用
     * @return
     */
    public List<Order> getAllOrder();

    /**
     * 获取某个用户的订单
     * @param userId
     * @return
     */
    public List<Order> getMyOrders(Integer userId);
}
