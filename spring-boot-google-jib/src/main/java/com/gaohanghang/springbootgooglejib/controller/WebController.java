package com.gaohanghang.springbootgooglejib.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @RequestMapping("/blog")
    public String index() {
        return "http://ghang.top";
    }
}

