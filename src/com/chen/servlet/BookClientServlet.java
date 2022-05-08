package com.chen.servlet;

import com.chen.bean.Book;
import com.chen.bean.Page;
import com.chen.service.BookService;
import com.chen.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookClientServlet",urlPatterns = "/client/BookClientServlet")
public class BookClientServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 给用户展示图书的分页数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用户点击图书管理显示部分数据,页码应该是用户传进来的
        String pn = request.getParameter("pn");
        Page<Book> page = bookService.getPage(pn, "4");
        page.setUrl("client/BookClientServlet?method=page");
        //将数据放到页面显示
        request.setAttribute("page" ,page);
        request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request,response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String max = request.getParameter("max");
        String min = request.getParameter("min");
        String pn = request.getParameter("pn");

        //查询价格区间的所有图书
        Page<Book> page = bookService.getPageByPrice(pn, "4", max, min);
        page.setUrl("client/BookClientServlet?method=pageByPrice&max="+max+"&min="+min);
        //返回页面显示
        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request,response);
    }
}
