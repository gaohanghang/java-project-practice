package com.gaohanghang.mail.web;

import com.gaohanghang.mail.common.model.Email;
import com.gaohanghang.mail.common.model.Result;
import com.gaohanghang.mail.service.IMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/01 17:08
 */
@Api(tags = "邮件管理")
@RestController
@RequestMapping("/mial")
public class mailController {

    @Autowired
    private IMailService mailService;

    @PostMapping("send")
    public Result send(Email mail) {
        try {
            mailService.sendFreemarker(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @ApiOperation("获取所有邮件")
    @PostMapping("list")
    public Result list(Email mail) {
        return mailService.listMail(mail);
    }
}
