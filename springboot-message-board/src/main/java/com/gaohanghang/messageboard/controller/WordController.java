package com.gaohanghang.messageboard.controller;

import com.gaohanghang.messageboard.dao.WordRepository;
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
@Api(description = "关于留言的操作", tags = "word")
public class WordController {

    @Autowired
    WordService wordService;


    @ApiOperation(value = "添加留言")
    @PostMapping(value = "/addWord")
    public Response addWord(@RequestParam("userId")Integer userId,
                            @RequestParam("title")String title,
                            @RequestParam("content")String content) {
        return wordService.addWord(userId, title, content);
    }

    @ApiOperation(value = "根据userId获取留言")
    @PostMapping(value = "/getWords")
    public Response getWords(@RequestParam("userId") Integer userId) {
        return wordService.getWords(userId);
    }
}
