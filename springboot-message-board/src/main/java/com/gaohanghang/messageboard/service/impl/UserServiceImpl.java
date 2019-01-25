package com.gaohanghang.messageboard.service.impl;

import com.alibaba.fastjson.JSON;
import com.gaohanghang.messageboard.dao.UserRepository;
import com.gaohanghang.messageboard.entity.User;
import com.gaohanghang.messageboard.service.UserService;
import com.gaohanghang.messageboard.utils.CommonTools;
import com.gaohanghang.messageboard.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Response getUser(Integer userId) {
        User user = null;
        if ((user = userRepository.findById(userId).get()) != null)
            return new Response("0", user);
        else
            return new Response("-1", "用户不存在");
    }

    @Override
    public Response userRegister(String userName, String userPassword) {
        if(CommonTools.isEmpty(userName))
            return new Response("-1","用户名不能为空");
        if(CommonTools.isEmpty(userPassword))
            return new Response("-1","用户密码不能为空");
        try {
            if(userRepository.findByUserName(userName)!=null)
                return new Response("-1","此用户名已经存在");
            User user = new User();
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            user.setRegisterTime(CommonTools.getCurrentTime());
            user = userRepository.save(user);
            user.setUserPassword("");
            return new Response("0", user);
        }catch (Exception e){
            return new Response("-1", "插入用户异常");
        }
    }

    @Override
    public Response userLogin(String userName, String userPassword) {
        if (CommonTools.isEmpty(userName))
            return new Response("-1", "用户名不能为空");
        if (CommonTools.isEmpty(userPassword))
            return new Response("-1", "用户密码不能为空");
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            if (user.getUserPassword().equals(userPassword)) {
                user.setUserPassword("");
                return new Response("0", user);
            } else {
                return new Response("-1", "密码错误");
            }
        } else {
            return new Response("-1", "用户不存在");
        }
    }
}
