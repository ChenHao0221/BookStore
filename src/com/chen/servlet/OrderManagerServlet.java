package com.chen.servlet;

import com.chen.Constants;
import com.chen.bean.Order;
import com.chen.service.OrderService;
import com.chen.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderManagerServlet", urlPatterns = "/manager/OrderManagerServlet")
public class OrderManagerServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    /**
     * 列出所有订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> list = orderService.getAllOrder();
        request.setAttribute("orders",list);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * 发货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderid");
        orderService.updateStatus("orderid", Constants.DELIVERING + "");
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}
