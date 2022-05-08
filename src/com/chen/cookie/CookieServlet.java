package com.chen.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CookieServlet",urlPatterns = "/CookieServlet")
public class CookieServlet extends BaseServlet {

    /**
     * 创建cookie
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器创建一个cookie
        Cookie cookie = new Cookie("username", "chenhao");

        //把cookie发给浏览器
        response.addCookie(cookie);

        response.getWriter().write("cookie已发送");
    }

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求头里获取到所有的cookie
        Cookie[] cookies = request.getCookies();
        //遍历，取出cookie的key和value
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            response.getWriter().write("cookie的name：" + name);
            response.getWriter().write("</br>cookie的value：" + value + "</br>");
        }

    }

    /**
     * 删除某个cookie
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if ("username".equals(c.getName())){
                cookie = c;
            }
        }

        //负数：不保存cookie
        //正数：最大存活时间
        //0：删除cookie
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.getWriter().write("cookie已经删除");
    }

    protected void persist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if ("username".equals(c.getName())){
                cookie = c;
            }
        }
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        response.getWriter().write("cookie设置的存活时间是一个小时");
    }

    /**
     * 为cookie设置路径
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void setPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("mycookie", "mycookievalue");
        //路径一定要在添加之前改
        //表示访问hello下的资源都会被带上
        //"/"代表的是服务器的根，不是项目的根
        cookie.setPath("/hello");
        response.addCookie(cookie);
        response.getWriter().write("cookie路径修改了");
    }

    /**
     * 修改cookie
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建一个同名cookie即可
        Cookie cookie = new Cookie("username", "chen");
        //浏览器就会将同名cookie覆盖
        response.addCookie(cookie);
        response.getWriter().write("cookie已经修改");
    }
}
