package com.chen.service;

import com.chen.bean.Order;
import com.chen.bean.OrderItem;

import java.util.List;

public interface OrderItemService {
    /**
     * 保存订单项
     * @param orderItem
     */
    public void saveItem(List<OrderItem> orderItem);

    /**
     * 获取订单的所有订单项
     * @param orderid
     * @return
     */
    public List<OrderItem> getOrderItems(String orderid);
}
