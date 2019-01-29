package com.gaohanghang.easyexceldemo.dao;

import com.gaohanghang.easyexceldemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:37
 */
public interface UserRepository extends JpaRepository<User,String> {
}
