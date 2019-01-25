package com.gaohanghang.messageboard.controller;

import com.gaohanghang.messageboard.utils.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/10 16:48
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Response exception(Exception e) {
        e.printStackTrace();
        return new Response("-1", "服务器发生异常");
    }
}
