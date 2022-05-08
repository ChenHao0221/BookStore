package com.chen.dao;

import com.chen.bean.Book;

import java.util.List;

public interface BookDao {
    /**
     * 获取所有图书
     * @return
     */
    public List<Book> getAllBook();

    public boolean addBook(Book book);

    public boolean delBook(Book book);

    /**
     * 修改一本图书
     * @param book  book是修改后的样子
     * @return
     */
    public boolean updateBook(Book book);

    public Book getBook(Book book);

    /**
     * 分页查找图书的方法
     * @param index
     * @param size
     * @return
     */
    public List<Book> getPageList(int index, int size);

    /**
     * 获取所有图书的记录数
     * @return
     */
    public int getTotalCount();

    /**
     * 按照价格获取图书的记录数
     * @param minPrice
     * @param maxPrice
     * @return
     */
    public int getTotalCountByPrice(double minPrice, double maxPrice);

    public List<Book> getPageByPrice(int index, int size, double minPrice, double maxPrice);

    public void updateStockAndSales(Integer bookid, Integer stock, Integer sales);

}
