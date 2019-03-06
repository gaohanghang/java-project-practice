package com.gaohanghang.springbootmanagebooks.service;

import com.gaohanghang.springbootmanagebooks.dao.UserMapper;
import com.gaohanghang.springbootmanagebooks.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false) // 有bean就注入，没有就不注入
    UserMapper userMapper;

    @Override
    public User checkUser(User user) {
        return userMapper.checkUser(user);
    }

    @Override
    public User checkManager(User user) {
        return userMapper.checkManager(user);
    }
}
