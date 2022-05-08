package com.chen.servlet;

import com.chen.bean.User;
import com.chen.service.UserService;
import com.chen.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/user/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserService us = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = us.login(new User(null, username, password, null));
        if (user == null){
            //登录失败,返回登录页面即可-用转发
            //在域中设置错误提示信息
            request.setAttribute("msg","用户名或密码错误！");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response); //绝对路径，从项目根开始（web是项目的根）
        }else {
            //登录成功，返回成功页面-用重定向
            response.sendRedirect(request.getContextPath() + "/pages/user/login-success.jsp"); //重定向是从服务器的根开始,因此request.getContextPath()获取到项目的根
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
