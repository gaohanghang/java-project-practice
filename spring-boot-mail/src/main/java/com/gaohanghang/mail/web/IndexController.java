package com.gaohanghang.mail.web;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 通用访问拦截匹配
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/01 17:02
 */
@Api(tags = "通用访问拦截匹配")
public class IndexController {

    /**
     * 页面跳转
     * @param url
     * @return
     */
    @GetMapping("{url}.shtml")
    public String page(@PathVariable("url") String url) {
        return url;
    }

    /**
     * 页面跳转(二级目录)
     * @param module
     * @param url
     * @return
     */
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        return module + "/" + url;
    }
}
