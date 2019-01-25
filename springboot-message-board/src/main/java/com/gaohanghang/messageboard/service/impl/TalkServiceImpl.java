package com.gaohanghang.messageboard.service.impl;

import com.alibaba.fastjson.JSON;
import com.gaohanghang.messageboard.dao.TalkRepository;
import com.gaohanghang.messageboard.dao.UserRepository;
import com.gaohanghang.messageboard.dao.WordRepository;
import com.gaohanghang.messageboard.entity.Talk;
import com.gaohanghang.messageboard.entity.Word;
import com.gaohanghang.messageboard.service.TalkService;
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
public class TalkServiceImpl implements TalkService {

    @Autowired
    TalkRepository talkRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Response getTalks(Integer userId) {
        List<Talk> talks = talkRepository.findAllByOrderByLeaveTime();
        if (talks.size() > 0) {
            return new Response("0", JSON.toJSONString(talks));
        } else {
            return new Response("0", "没有留言");
        }
    }

    @Override
    public Response addTalk(Integer userId, String content) {
        if (userRepository.findById(userId).get() == null)
            return new Response("-1", "用户不存在");
        if (CommonTools.isEmpty(content))
            return new Response("-1", "内容不能为空");
        Talk talk = new Talk();
        talk.setUserId(userId);
        talk.setContent(content);
        talk.setLeaveTime(CommonTools.getCurrentTime());
        talkRepository.save(talk);
        return new Response("0", JSON.toJSONString(talk));
    }

    @Override
    public Response getSize(Integer userId) {
        List<Talk> talks = talkRepository.findAllByOrderByLeaveTime();
        if (talks.size() > 0) {
            return new Response("0", JSON.toJSONString(talks.size()));
        } else {
            return new Response("0", "没有留言");
        }
    }
}
