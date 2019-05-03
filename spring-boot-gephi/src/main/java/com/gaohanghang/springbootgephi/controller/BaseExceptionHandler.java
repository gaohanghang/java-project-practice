package com.gaohanghang.springbootgephi.controller;

import com.gaohanghang.springbootgephi.entity.Result;
import com.gaohanghang.springbootgephi.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){

        e.printStackTrace();        
        return new Result(false, StatusCode.ERROR, "执行出错");
    }

}
