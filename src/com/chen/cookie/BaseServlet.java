package com.chen.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 抽取出BaseServlet之后，UserServlet里面只需要编写相应的处理逻辑即可
 */

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在还未获取数据之前设置编码(在getParameter之前)
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        try {
            //反射机制
            Method method2 = this.getClass().getDeclaredMethod(method,HttpServletRequest.class, HttpServletResponse.class);
            //把方法的权限放大
            method2.setAccessible(true);
            method2.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
