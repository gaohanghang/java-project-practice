package com.gaohanghang.messageboard.controller;

import com.gaohanghang.messageboard.service.TalkService;
import com.gaohanghang.messageboard.service.WordService;
import com.gaohanghang.messageboard.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 12:06
 */
@RestController
@Api(description = "关于聊天的操作", tags = "word")
public class TalkController {

    @Autowired
    TalkService talkService;


    @ApiOperation(value = "添加聊天")
    @PostMapping(value = "/addTalk")
    public Response addTalk(@RequestParam("userId")Integer userId,
                            @RequestParam("content")String content) {
        return talkService.addTalk(userId, content);
    }

    @ApiOperation(value = "根据userId获取聊天")
    @PostMapping(value = "/getTalks")
    public Response getWords(@RequestParam("userId") Integer userId) {
        return talkService.getTalks(userId);
    }

    @ApiOperation(value = "获取聊天条数")
    @PostMapping(value = "/getSize")
    public Response getSize(@RequestParam("userId") Integer userId) {
        return talkService.getSize(userId);
    }
}
