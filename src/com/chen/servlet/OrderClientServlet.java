package com.chen.servlet;

import com.chen.Constants;
import com.chen.bean.Cart;
import com.chen.bean.Order;
import com.chen.bean.User;
import com.chen.service.OrderService;
import com.chen.service.impl.OrderServiceImpl;
import com.chen.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderClientServlet",urlPatterns = "/client/OrderClientServlet")
public class OrderClientServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证用户是否登录
        HttpSession session = request.getSession();
        Cart cart = WebUtils.getCart(request);
        User loginUser = WebUtils.getLoginUser(request);
        String orderid = orderService.checkout(cart, loginUser);
        session.setAttribute("orderId", orderid);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 列出当前用户的所有订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.getLoginUser(request);
        List<Order> list = null;
        try {
            list = orderService.getMyOrders(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //域中保存所有的订单
        request.setAttribute("orders", list);

        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    /**
     * 确认收获
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void received(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderid");
        orderService.updateStatus("orderid", Constants.DELIVERED + "");
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}
