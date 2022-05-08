package com.chen.utils;

import com.chen.bean.Cart;
import com.chen.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Web相关工具
 */

public class WebUtils {

    /**
     * 传入request对象，将request中的数据封装成对象
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    public static<T> T param2bean(HttpServletRequest request, T t){
        //封装对象并返回
        //1、获取所有声明的
        Field[] fields = t.getClass().getDeclaredFields();

        //2、每个属性都有name值，属性名
        for (Field field : fields) {
            //获取属性名
            String name = field.getName();
            //在request中获取相应的属性值
            String value = request.getParameter(name);

            try {
                BeanUtils.setProperty(t, name, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    //相较于上面的param2bean更加智能的方法！
    public static<T> T param2bean2(HttpServletRequest request, T t){
        //BeanUtils.populate(bean, properties);
        //将map中的键值对直接映射到javaBean中
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(t, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
