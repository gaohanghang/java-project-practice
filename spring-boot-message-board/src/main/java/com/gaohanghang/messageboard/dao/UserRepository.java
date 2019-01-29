package com.gaohanghang.messageboard.dao;

import com.gaohanghang.messageboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:56
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}