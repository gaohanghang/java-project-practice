package com.gaohanghang.messageboard.service;

import com.gaohanghang.messageboard.utils.Response;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:59
 */
public interface WordService {
    Response getWords(Integer userId);
    Response addWord(Integer userId,String title,String content);
}
