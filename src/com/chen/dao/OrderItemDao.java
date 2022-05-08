package com.chen.dao;

import com.chen.bean.OrderItem;

import java.util.List;

/**
 * 操作订单项的Dao
 */
public interface OrderItemDao {
    public List<OrderItem> getOrderItems(String orderId);

    public int saveOrderItem(OrderItem item);

    public int saveBatch(List<OrderItem> item);
}
