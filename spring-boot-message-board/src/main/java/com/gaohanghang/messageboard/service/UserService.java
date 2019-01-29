package com.gaohanghang.messageboard.service;

import com.gaohanghang.messageboard.utils.Response;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:59
 */
@Service
public interface UserService {
    Response getUser(Integer userId);
    Response userRegister(String userName,String userPassword);
    Response userLogin(String userName,String userPassword);
}