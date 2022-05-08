package com.chen.servlet;

import com.chen.bean.Book;
import com.chen.bean.Cart;
import com.chen.bean.CartItem;
import com.chen.service.BookService;
import com.chen.service.impl.BookServiceImpl;
import com.chen.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CartServlet", urlPatterns = "/client/CartServlet")
public class CartServlet extends BaseServlet {
    BookService bs = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.param2bean2(request, new Book());
        //购物车的整个内容在session中保存
        HttpSession session = request.getSession();
        //获取购物车
        Cart cart = WebUtils.getCart(request);

        Book one = bs.getOne(book);
        cart.addBook2Cart(one);
        session.setAttribute("title" ,one.getTitle());
        //Referer（请求地址）只是指上次的请求行
        //get：请求地址包括请求数据
        //post：不包括请求数据
        String refer = request.getHeader("Referer");
        response.sendRedirect(refer);
    }

    /**
     * 使用Ajax加图书加入购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = WebUtils.param2bean2(request, new Book());
        //购物车的整个内容在session中保存
        HttpSession session = request.getSession();
        //获取购物车
        Cart cart = WebUtils.getCart(request);

        Book one = bs.getOne(book);
        cart.addBook2Cart(one);
        //将图书加入成功之后，只需要返回部分数据
        //不需要将数据放入session中，直接返回即可
        int totalCount = cart.getTotalCount();
        //获取刚才添加的图书的书名
        String title = one.getTitle();
        //为了js解析方便，我们写json格式的数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("title", title);
        Gson gson = new Gson();
        //将map转为json
        String json = gson.toJson(map);
        response.getWriter().write(json);

    }

    /**
     * 删除某个购物项
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        cart.deleteItem(request.getParameter("id"));

        String refer = request.getHeader("Referer");
        response.sendRedirect(refer);
    }

    /**
     * 修改数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        cart.updateCount(request.getParameter("id"), request.getParameter("count"));

        String refer = request.getHeader("Referer");
        response.sendRedirect(refer);
    }

    protected void updateAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        String bookid = request.getParameter("id");
        cart.updateCount(bookid, request.getParameter("count"));

        //返回部分内容
        //找到修改的购物项
        CartItem cartItem = cart.getItem(bookid);
        double totalPrice = cartItem.getTotalPrice();
        int totalCount = cart.getTotalCount();
        double totalMoney = cart.getTotalMoney();

        //将处理好的数据组装返回
        Map<String, Object> map = new HashMap<>();
        map.put("totalPrice", totalPrice);
        map.put("totalCount", totalCount);
        map.put("totalMoney", totalMoney);
        //将数据转换为json字符串返回
        Gson gson = new Gson();
        //响应数据直接写出去就行，AjaxRequest中的data就能接收到
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = WebUtils.getCart(request);
        cart.clear();

        String refer = request.getHeader("Referer");
        response.sendRedirect(refer);
    }
}
