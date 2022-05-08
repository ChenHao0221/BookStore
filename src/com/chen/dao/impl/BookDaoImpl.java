package com.chen.dao.impl;

import com.chen.bean.Book;
import com.chen.dao.BaseDao;
import com.chen.dao.BookDao;

import java.util.List;

/**
 * 操作图书的DAO
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> getAllBook() {
        String sql = "select id,title,author,price,sales,stock,img_path ImgPath from bs_book2";
        //返回所有的图书
        return getBeanList(sql);
    }

    @Override
    public boolean addBook(Book book) {
        String sql = "insert into bs_book2(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        int update = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return update > 0;
    }

    @Override
    public boolean delBook(Book book) {
        String sql = "delete from bs_book2 where id=?";
        int update = update(sql, book.getId());
        return update > 0;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "update bs_book2 set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        int update = update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return update > 0;
    }

    @Override
    public Book getBook(Book book) {
        String sql = "select id,title,author,price,sales,stock,img_path ImgPath from bs_book2 where id=?";
        return getBean(sql, book.getId());
    }

    @Override
    public List<Book> getPageList(int index, int size) {
        String sql = "select id,title,author,price,sales,stock,img_path ImgPath from bs_book2 limit ?,?";
        return getBeanList(sql, index, size);
    }

    /**
     * 根据价格查询图书
     * @param index
     * @param size
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public List<Book> getPageByPrice(int index, int size, double minPrice, double maxPrice) {
        String sql = "select id,title,author,price,sales,stock,img_path ImgPath from bs_book2 where price between ? and ? limit ?,?";
        return getBeanList(sql, minPrice, maxPrice, index, size);
    }

    @Override
    public void updateStockAndSales(Integer bookid, Integer stock, Integer sales) {

    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from bs_book2";
        Object object = getSingleValue(sql);
        int parseInt = 0;
        try {
            parseInt = Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return parseInt;
    }

    /**
     * 根据图书价格查询出所有记录数
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public int getTotalCountByPrice(double minPrice, double maxPrice) {
        String sql = "select count(*) from bs_book2 where price between ? and ?";
        Object object = getSingleValue(sql, minPrice, maxPrice);
        int i = 0;
        try {
            i = Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }
}
