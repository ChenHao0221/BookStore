package com.chen.service.impl;

import com.chen.bean.*;
import com.chen.dao.OrderDao;
import com.chen.dao.impl.OrderDaoImpl;
import com.chen.service.BookService;
import com.chen.service.OrderItemService;
import com.chen.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemService itemService = new OrderItemServiceImpl();
    BookService bookService = new BookServiceImpl();

    @Override
    public String checkout(Cart cart, User user) {
        //结账操作，把购物车里的数据封装并保存
        //1、封装订单对象
        //orderId需要算法生成
        long millis = System.currentTimeMillis();
        String orderId = millis + "" + user.getId();
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setOrderId(orderId);
        order.setTotalMoney(cart.getTotalMoney());
        order.setStatus(0);
        order.setUserId(user.getId());

        //2、封装订单项对象
        List<CartItem> allItems = cart.getAllItems();
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : allItems) {
            //封装一个订单项
            OrderItem item = new OrderItem(cartItem.getBook().getTitle(), cartItem.getCount(), cartItem.getBook().getPrice(), cartItem.getTotalPrice(), orderId);
            orderItems.add(item);
        }

        //3、保存订单
        orderDao.saveOrder(order);

        //4、保存订单项
        itemService.saveItem(orderItems);

        //5、修改相应库存
        for (CartItem cartItem : allItems) {
            //图书信息应该从数据库中得到
            Book book = cartItem.getBook();
            Book one = bookService.getOne(book);
            int count = cartItem.getCount();
            one.setStock(one.getStock() - count);
            one.setSales(one.getSales() + count);
            bookService.update(one);
        }
        //清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public void updateStatus(String orderid, String status) {
        Order order = new Order();
        order.setOrderId(orderid);
        int parseInt = 0;
        try {
            parseInt = Integer.parseInt(status);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        order.setStatus(parseInt);
        orderDao.updateStatus(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getOrderList();
    }

    @Override
    public List<Order> getMyOrders(Integer userId) {
        return orderDao.getOrderByUserId(userId);
    }
}
