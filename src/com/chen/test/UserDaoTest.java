package com.chen.test;

import com.chen.bean.User;
import com.chen.dao.UserDao;
import com.chen.dao.impl.UserDaoImpl;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void test(){
        UserDao ud = new UserDaoImpl();
        User user = ud.getUserByUserNameAndPassword(new User(null, "tomcat", "123456", null));
        System.out.println(user);
    }

    @Test
    public void test2(){
        UserDao ud = new UserDaoImpl();
        boolean b = ud.registUser(new User(null, "tomcat2", "123456", null));
        System.out.println(b);
    }
}
