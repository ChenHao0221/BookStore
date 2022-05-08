package com.chen.servlet;

import com.chen.bean.User;
import com.chen.service.UserService;
import com.chen.service.impl.UserServiceImpl;
import com.chen.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistServlet",urlPatterns = "/user/RegistServlet")
public class RegistServlet extends HttpServlet {

    private UserService us = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = WebUtils.param2bean(request, new User());

        boolean b = us.regist(new User(null, username, password, email));
        if (b){
            //注册成功，返回成功页面 重定向
            response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
        }else {
            //注册失败，返回注册页面重新注册 转发
            //在域中设置注册失败提示信息
            request.setAttribute("msg","用户已存在");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
