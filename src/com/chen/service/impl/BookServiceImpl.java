package com.chen.service.impl;

import com.chen.bean.Book;
import com.chen.bean.Page;
import com.chen.dao.BookDao;
import com.chen.dao.impl.BookDaoImpl;
import com.chen.service.BookService;

import java.util.List;

/**
 * 图书业务逻辑实现
 */
public class BookServiceImpl implements BookService {

    private BookDao bd = new BookDaoImpl();

    @Override
    public boolean add(Book book) {
        return bd.addBook(book);
    }

    @Override
    public boolean update(Book book) {
        return bd.updateBook(book);
    }

    @Override
    public boolean delete(Book book) {
        return bd.delBook(book);
    }

    @Override
    public Book getOne(Book book) {
        return bd.getBook(book);
    }

    @Override
    public List<Book> getAll() {
        return bd.getAllBook();
    }

    /**
     * 获取分页数据
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> getPage(String pageNo, String pageSize) {
        //将用户传入的数据先封装部分
        Page<Book> page = new Page<Book>();
        //将用户传入的数据转型并封装，设置默认值
        int pn = 1;
        int ps = page.getPageSize();
        try {
            pn = Integer.parseInt(pageNo);
            pn = pn > 0 ? pn : 1;
            ps = Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //要使用totalCount，所有还需要查数据库
        //先要设置页面大小
        page.setPageSize(ps);
        int totalCount = bd.getTotalCount();
        //再设置总记录数
        page.setTotalCount(totalCount);
        //这样就可以算出总页码，要调用getTotalPage()
        page.setPageNo(pn);

        //查询数据并封装
        List<Book> list = bd.getPageList(page.getIndex(), page.getPageSize());
        page.setPageData(list);
        return page;
    }

    /**
     * 按照价格查找图书
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> getPageByPrice(String pageNo, String pageSize, String maxPrice, String minPrice) {
        double min = 0.0;
        double max = Double.MAX_VALUE;
        try {
            min = Double.parseDouble(minPrice);
            max = Double.parseDouble(maxPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将用户传入的数据先封装部分
        Page<Book> page = new Page<Book>();
        //将用户传入的数据转型并封装，设置默认值
        int pn = 1;
        int ps = page.getPageSize();
        try {
            pn = Integer.parseInt(pageNo);
            pn = pn > 0 ? pn : 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            ps = Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //按照价格区间获取总记录数
        int count = bd.getTotalCountByPrice(min, max);
        page.setTotalCount(count);

        //将页码及页面大小设置进page对象
        page.setPageSize(ps);
        //这是最后设置的
        page.setPageNo(pn);

        List<Book> list = bd.getPageByPrice(page.getIndex(), page.getPageSize(), min, max);

        //封装进page对象
        page.setPageData(list);

        return page;
    }

}
