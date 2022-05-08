package com.chen.service.impl;

import com.chen.bean.User;
import com.chen.dao.UserDao;
import com.chen.dao.impl.UserDaoImpl;
import com.chen.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao ud = new UserDaoImpl();
    @Override
    public User login(User user) {
        return ud.getUserByUserNameAndPassword(user);
    }

    @Override
    public boolean regist(User user) {
        return ud.registUser(user);
    }

    @Override
    public boolean checkuser(User user) {
        User byUserName = ud.getUserByUserName(user);
        return byUserName == null;
    }
}
