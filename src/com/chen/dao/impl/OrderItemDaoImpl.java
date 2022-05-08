package com.chen.dao.impl;

import com.chen.bean.OrderItem;
import com.chen.dao.BaseDao;
import com.chen.dao.OrderItemDao;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public List<OrderItem> getOrderItems(String orderId) {
        String sql = "select id,title,count,price,total_price totalPrice,order_id orderId from bs_order_item where order_id=?";
        return getBeanList(sql,orderId);
    }

    @Override
    public int saveOrderItem(OrderItem item) {
        String sql = "insert into bs_order_item(title,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql, item.getTitle(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
    }

    /**
     * 执行批量保存
     * @param params
     * @return
     */
    @Override
    public int saveBatch(List<OrderItem> params){
        String sql = "insert into bs_order_item(title,count,price,total_price,order_id) values(?,?,?,?,?)";
        Object[][] objs = new Object[params.size()][5];
        int count = 0;
        for (OrderItem item : params) {
            objs[count++] = new Object[]{item.getTitle(), item.getCount(), item.getPrice(), item.getTotalPrice(), item.getOrderId()};
        }
        batch(sql, objs);

        return 1;
    }
}
