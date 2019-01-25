package com.gaohanghang.messageboard.controller;

import com.gaohanghang.messageboard.service.UserService;
import com.gaohanghang.messageboard.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 12:06
 */
@RestController
@EnableSwagger2 // 启动swagger注解
@Api(description = "关于用户的操作", tags = "user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 注册新用户
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/userRegister")
    public Response userRegister(@RequestParam("userName")String userName,
                                 @RequestParam("userPassword")String userPassword) {
        return userService.userRegister(userName, userPassword);
    }

    /**
     * 根据userId获取用户
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据id查询用户")
    @PostMapping(value = "/getUser")
    public Response getUser(@RequestParam("userId") Integer userId) {
        return userService.getUser(userId);
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param userPassword
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/userLogin")
    public Response userLogin(@RequestParam("userName")String userName,
                              @RequestParam("userPassword")String userPassword) {
        return userService.userLogin(userName, userPassword);
    }
}
