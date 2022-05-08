package com.chen.dao;

import com.chen.bean.User;

public interface UserDao {

    /**
     * 根据User中封装的用户名和密码，查询详细的用户信息
     * @param user
     * @return
     */
    public User getUserByUserNameAndPassword(User user);

    /**
     * 注册保存用户
     * @param user
     * @return
     */
    public boolean registUser(User user);

    public  User getUserByUserName(User user);
}
