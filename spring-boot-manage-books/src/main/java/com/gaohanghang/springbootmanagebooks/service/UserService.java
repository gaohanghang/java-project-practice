package com.gaohanghang.springbootmanagebooks.service;

import com.gaohanghang.springbootmanagebooks.entity.User;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:46
 */
public interface UserService {
    User checkUser(User user);

    User checkManager(User user);
}
