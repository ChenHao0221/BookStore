package com.chen.test;

import com.chen.bean.Book;
import com.chen.bean.Page;
import com.chen.dao.BookDao;
import com.chen.dao.impl.BookDaoImpl;
import com.chen.service.BookService;
import com.chen.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

public class BookDaoTest {
    BookDao bd = new BookDaoImpl();

    /**
     * 获取所有图书
     */
    @Test
    public void test(){
        List<Book> allBook = bd.getAllBook();
        System.out.println(allBook);
    }

    /**
     * 添加图书
     */
    @Test
    public void test2(){
        Book book = new Book(null, "C++", "leifengyang", 50, 0, 200, null);
        boolean b = bd.addBook(book);
        System.out.println(b);
    }

    /**
     * 获取一本图书
     */
    @Test
    public void test3(){
        Book b = new Book();
        b.setId(1);
        Book book = bd.getBook(b);
        System.out.println(book);
    }

    @Test
    public void test4(){
        BookService bs = new BookServiceImpl();
        Page<Book> page = bs.getPage("2", "4");
        System.out.println(page.getPageData());
    }

    @Test
    public void test5(){
        BookService bs = new BookServiceImpl();
        Page<Book> page = bs.getPageByPrice("1", "4", "60", "0");

        System.out.println(page.getPageData());
        System.out.println(page.getPageNo());
        System.out.println(page.getPageSize());
        System.out.println(page.getTotalCount());
        System.out.println(page.getTotalPage());
    }
}
