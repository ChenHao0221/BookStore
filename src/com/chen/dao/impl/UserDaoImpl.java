package com.chen.dao.impl;

import com.chen.bean.User;
import com.chen.dao.BaseDao;
import com.chen.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User getUserByUserNameAndPassword(User user) {
        String sql = "select * from bs_user2 where username=? and password=?"; //?就是我们要传的可变参数
        User bean = this.getBean(sql, user.getUsername(), user.getPassword());
        return bean;
    }

    /**
     * 注册，就是保存到数据库
     * @param user
     * @return
     */
    @Override
    public boolean registUser(User user) {
        String sql = "insert into bs_user2(username,password,email) value(?,?,?)";
        //update > 0 表示保存成功
        int update = this.update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return update > 0;
    }

    @Override
    public User getUserByUserName(User user) {
        String sql = "select * from bs_user2 where username=?";
        return getBean(sql, user.getUsername());
    }
}
