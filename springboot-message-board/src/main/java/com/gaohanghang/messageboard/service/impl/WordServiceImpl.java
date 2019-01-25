package com.gaohanghang.messageboard.service.impl;

import com.alibaba.fastjson.JSON;
import com.gaohanghang.messageboard.dao.UserRepository;
import com.gaohanghang.messageboard.dao.WordRepository;
import com.gaohanghang.messageboard.entity.Word;
import com.gaohanghang.messageboard.service.WordService;
import com.gaohanghang.messageboard.utils.CommonTools;
import com.gaohanghang.messageboard.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:59
 */
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WordRepository wordRepository;

    @Override
    public Response getWords(Integer userId) {
        List<Word> words = wordRepository.findAll();
        if (words.size() > 0) {
            return new Response("0", words);
        } else {
            return new Response("0", "没有留言");
        }
    }

    @Override
    public Response addWord(Integer userId, String title, String content) {
        if (userRepository.findById(userId).get() == null)
            return new Response("-1", "用户不存在");
        if (CommonTools.isEmpty(title))
            return new Response("-1", "标题不能为空");
        if (CommonTools.isEmpty(content))
            return new Response("-1", "内容不能为空");
        Word word = new Word();
        word.setUserId(userId);
        word.setTitle(title);
        word.setContent(content);
        word.setLeaveTime(CommonTools.getCurrentTime());
        wordRepository.save(word);
        return new Response("0", word);
    }
}
