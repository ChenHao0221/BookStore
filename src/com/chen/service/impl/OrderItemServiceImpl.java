package com.chen.service.impl;

import com.chen.bean.OrderItem;
import com.chen.dao.OrderItemDao;
import com.chen.dao.impl.OrderItemDaoImpl;
import com.chen.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    OrderItemDao itemDao = new OrderItemDaoImpl();

    @Override
    public void saveItem(List<OrderItem> orderItem) {
        itemDao.saveBatch(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItems(String orderid) {
        return itemDao.getOrderItems(orderid);
    }
}
