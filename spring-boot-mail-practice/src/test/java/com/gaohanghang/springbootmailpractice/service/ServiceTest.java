package com.gaohanghang.springbootmailpractice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/13 01:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sayHelloTest() {
        mailService.sayHello();
    }

    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail("18595739675@163.com", "这是第一封邮件", "大家好，这是我的第一封邮件！");
    }


    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content = "<html>\n" +
                "<body>\n" +
                    "<h3> hello world, 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("18595739675@163.com", "这是一封HTML邮件", content);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String filePath = "/users/gaohanghang/1.jpg";
        mailService.sendAttachmentsMail("18595739675@163.com", "这是一封带附件的邮件邮件", "这是一篇带附件的邮件邮件",  filePath);
    }

    @Test
    public void sendInlineMail() {
        String imgPath = "/users/gaohanghang/1.jpg";
        String rscId = "neo001";
        String content = "<html><body> 这是有图片的邮件:<img src=\'cid:" + rscId + "\'> </img><img src=\'cid:" + rscId + "\'> </img></body></html>";
        mailService.sendInlineResourceMail("18595739675@163.com", "这是一个图片邮件", content, imgPath, rscId);
    }

    @Test
    public void testTemplateMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("id", "006");

        String emailTemplate = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("18595739675@163.com", "这是一个模板邮件", emailTemplate);
    }
}
