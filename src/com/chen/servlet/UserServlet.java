package com.chen.servlet;

import com.chen.bean.User;
import com.chen.service.UserService;
import com.chen.service.impl.UserServiceImpl;
import com.chen.utils.WebUtils;
import com.google.code.kaptcha.Constants;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * 处理所有和用户相关的请求
 */
@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService us = new UserServiceImpl();

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
        //获取用户输入的验证码，获取session中的验证码
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //如果验证码一致则注册，否则回到注册页面并提示验证码错误
        if (!sessionCode.equals(code)){
            //验证码不相同
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }

        User user = WebUtils.param2bean(request, new User());

        boolean b = us.regist(user);
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

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        User user2 = WebUtils.param2bean2(request, new User());
        User user = us.login(user2);
        //将用户保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

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

    /**
     * 用户登出操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //销毁session即可
        session.invalidate();
        //点完登出以后返回商城首页
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    protected void checkuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = WebUtils.param2bean2(request, new User());

        boolean b = us.checkuser(user);

        if (b){
            response.getWriter().write("用户名可用"); //write写的内容就是ajax会收到的数据
        }else {
            response.getWriter().write("用户已存在");
        }
    }
}
