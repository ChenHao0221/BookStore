package com.chen.service;

import com.chen.bean.Book;
import com.chen.bean.Page;

import java.util.List;

/**
 * 图书业务逻辑
 */
public interface BookService {
        public boolean add(Book book);

        public boolean update(Book book);

        public boolean delete(Book book);

        public Book getOne(Book book);

        public List<Book> getAll();

        /**
         * 返回分页数据
         * @return
         */
        public Page<Book> getPage(String pageNo, String pageSize);

        public Page<Book> getPageByPrice(String pageNo, String pageSize, String maxPrice, String minPrice);
}
