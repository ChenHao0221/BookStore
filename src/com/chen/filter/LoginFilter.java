package com.chen.filter;

import com.chen.bean.User;
import com.chen.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/client/OrderClientServlet")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter已经启动");
        HttpServletRequest request = (HttpServletRequest) req;
        User user = WebUtils.getLoginUser(request);
        if (user == null){
            request.setAttribute("msg", "此操作需要登录！");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
