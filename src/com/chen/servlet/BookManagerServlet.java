package com.chen.servlet;

import com.chen.bean.Book;
import com.chen.bean.Page;
import com.chen.service.BookService;
import com.chen.service.impl.BookServiceImpl;
import com.chen.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookManagerServlet",urlPatterns = "/manager/BookManagerServlet")
public class BookManagerServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 显示分页数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用户点击图书管理显示部分数据,页码应该是用户传进来的
        String pn = request.getParameter("pn");
        Page<Book> page = bookService.getPage(pn, "4");
        page.setUrl("manager/BookManagerServlet?method=page");
        //将数据放到页面显示
        request.setAttribute("page" ,page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 显示图书列表（现在没用了）
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //查出所有图书数据并显示
        List<Book> list = bookService.getAll();
        //图书查出以后交给页面显示book_manager.jsp
        request.setAttribute("list" ,list);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 图书添加  --->  所有的都在update方法中处理
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //将提交的图书信息封装为book对象，表单的name应该和对象一一对应
        Book book = WebUtils.param2bean2(request, new Book());
        //将图书保存到数据库
        boolean b = bookService.add(book);
            //保存成功，重回列表页面列表显示,http://localhost:8080/BookStore/manager/BookManagerServlet?method=list
            response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page");
    }

    /**
     * 图书修改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pn = request.getParameter("pn");
        //封装要删除的book
        Book book = WebUtils.param2bean2(request, new Book());
        //调用删除方法
        bookService.delete(book);

        //回到列表显示
        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn="+pn);
    }

    /**
     * 查出某本图书的详细信息，显示到页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //按照id查出某本图书
        Book book = WebUtils.param2bean2(request, new Book());
        //获取详细信息
        Book one = bookService.getOne(book);
        //回到编辑页面显示
        request.setAttribute("book", one);
        //转发到页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    /**
     * 图书修改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pn = request.getParameter("pn");
        //封装修改的图书信息
        Book book = WebUtils.param2bean2(request, new Book());
        //由于添加和修改操作，封装出的book对象，id有差别，所有可以通过id直接判断是否为修改或添加操作
        if (book.getId() == 0){
            //添加图书
            bookService.add(book);
        }else {
            //修改图书
            bookService.update(book);
        }
        //修改完之后返回列表页面
        response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn=" + pn);
    }
}
