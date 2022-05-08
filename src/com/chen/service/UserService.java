package com.chen.service;

import com.chen.bean.User;

/**
 * 完成用户的登录注册
 */
public interface UserService {
    public User login(User user);

    public boolean regist(User user);

    public boolean checkuser(User user);
}
