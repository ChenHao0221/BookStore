package com.chen.test;

import com.chen.bean.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTest {
    @Test
    public void test1(){
        //BeanUtils.setProperty(bean, name, value);
        //bean代表要给哪个对象设置属性值，name代表要设置的属性名，value代表要设置的值
        User user = new User();
        System.out.println(user);
        try {
            BeanUtils.setProperty(user, "username", "小明");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
