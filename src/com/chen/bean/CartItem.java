package com.chen.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 每个购物项
 */
public class CartItem implements Serializable {
    private Book book;

    //购买的数量
    private int count;

    //总金额
    private double totalPrice;

    public CartItem() {
    }

    public CartItem(Book book, int count, double totalPrice) {
        this.book = book;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotalPrice() {
        //将获取到的价格使用BigDecimal包装
        BigDecimal price = new BigDecimal(getBook().getPrice() + "");
        BigDecimal count = new BigDecimal(getCount() + "");
        BigDecimal multiply = price.multiply(count);
        return multiply.doubleValue();
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
