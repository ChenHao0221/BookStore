package com.chen.dao;

import com.chen.bean.Order;

import java.util.List;

/**
 * 操作订单的Dao
 */
public interface OrderDao {
    public int saveOrder(Order order);

    public int updateStatus(Order order);

    public List<Order> getOrderList();

    public List<Order> getOrderByUserId(Integer userId);


}
