package com.gaohanghang.springbootmanagebooks.dao;

import com.gaohanghang.springbootmanagebooks.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:26
 */
public interface UserMapper {
    //通过username和passwd 验证用户 reader
    User checkUser(@Param("user") User user);
    User checkManager(@Param("user") User user);
}
