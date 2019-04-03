package com.gaohanghang.springbootmanagebooks.controller;

import com.gaohanghang.springbootmanagebooks.entity.ProcessResult;
import com.gaohanghang.springbootmanagebooks.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    static private Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    MailService mailService;

    @RequestMapping(value = "/{validation}/email", method = RequestMethod.GET)
    public ProcessResult remindReturn(@PathVariable(value = "validation") Integer val) {
       ProcessResult pr = null;
       if (val == null) return new ProcessResult(false);
       if (!val.equals("czctalent")&&!val.equals("czhtalent")) return new ProcessResult(false);
       if (val.equals("czctalent")) {
           /*
                对于已到期且未归还的图书，系统通过 Email 自动通知读者
            */
           /*提醒还书*/
           try {
               mailService.processReturnReminder();
               pr = new ProcessResult(true);
           } catch (Exception e) {
               pr = new ProcessResult(false);
           }
       } else if (val.equals("czhtalent")) {
           /*
                若读者预约 的书已到，系统则自动通过 Email 通知该读者来办理借书手续。
            */
           /*预约提醒*/
           try {
               mailService.processResReminder();
               pr = new ProcessResult(true);
           } catch (Exception e) {
               pr = new ProcessResult(false);
           }
       }
       return pr;
    }
}
