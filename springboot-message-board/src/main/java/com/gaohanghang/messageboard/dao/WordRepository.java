package com.gaohanghang.messageboard.dao;

import com.gaohanghang.messageboard.entity.User;
import com.gaohanghang.messageboard.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:57
 */
public interface WordRepository extends JpaRepository<Word,Integer>{
}