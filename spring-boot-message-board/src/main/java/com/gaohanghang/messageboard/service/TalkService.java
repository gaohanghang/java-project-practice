package com.gaohanghang.messageboard.service;

import com.gaohanghang.messageboard.utils.Response;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:59
 */
public interface TalkService {
    Response getTalks(Integer userId);
    Response addTalk(Integer userId, String content);

    Response getSize(Integer userId);
}
