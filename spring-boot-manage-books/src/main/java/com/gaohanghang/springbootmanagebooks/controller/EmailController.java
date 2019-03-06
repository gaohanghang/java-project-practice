package com.gaohanghang.springbootmanagebooks.controller;

import com.gaohanghang.springbootmanagebooks.entity.ProcessResult;
import com.gaohanghang.springbootmanagebooks.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/03/01 11:37
 */
@RestController
@RequestMapping("/managebooks")
public class EmailController {

    @Autowired
    MailService mailService;

    @RequestMapping(value = "/{validation}/email", method = RequestMethod.GET)
    public ProcessResult remindReturn(@PathVariable(value = "validation") Integer val) {
       return null;
    }
}
