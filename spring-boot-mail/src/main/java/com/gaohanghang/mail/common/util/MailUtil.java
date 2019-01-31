package com.gaohanghang.mail.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步发送
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/29 12:20
 */
public class MailUtil {
    private Logger logger = LoggerFactory.getLogger(MailUtil.class);

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(6);

    // 统计
    private final AtomicInteger count = new AtomicInteger(1);

    /**
     * 获取 Sender 多实例发送
     * @return
     */
    public static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.mxhichina.com");
        sender.setPort(25);
        sender.setUsername("admin@52itstyle.com");
        sender.setPassword("123456");
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout",1000+"");
        p.setProperty("mail.smtp.auth","true");
        sender.setJavaMailProperties(p);
        return sender;
    }

    public static void main(String[] args) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("admin@52itstyle.com");
        message.setTo("345849402@qq.com");
        message.setSubject("测试");
        message.setText("测试");
        createMailSender().send(message);
    }

    public void start(final JavaMailSender mailSender, final SimpleMailMessage message) {
        service.execute(() -> {
            try {
                if (count.get() == 2) {
                    service.shutdown();
                    logger.info("the task is down");
                }
                logger.info("start send email and the index is " + count);
                mailSender.send(message);
            } catch (MailException e) {
                logger.error("send email fail", e);
            }
        });
    }

    public void startHtml(final JavaMailSender mailSender, final MimeMessage message) {
        service.execute(() -> {
            try {
                if (count.get() == 2) {
                    service.shutdown();
                    logger.info("the task is down");
                }
                logger.info("start send email and the index is " + count);
                mailSender.send(message);
                logger.info("send email success");
            } catch (MailException e) {
                logger.error("send email fail", e);
            }
        });
    }
}
