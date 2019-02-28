package com.gaohanghang.springbootmanagebooks.service;

import com.gaohanghang.springbootmanagebooks.dao.MailMapper;
import com.gaohanghang.springbootmanagebooks.entity.MailDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:55
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired(required = false) //required = false 有就注入，没有就不注入
    private MailMapper mailMapper;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    // 还书提醒
    @Override
    public void processReturnReminder() {
        List<MailDetail> list = mailMapper.returnReminder();
        for (MailDetail m : list) {
            String subject = "快还书";
            String userName = m.getUserName();
            String bname = m.getBtime();
            String btime = m.getBtime();
            String content = "尊敬的" + userName + ": "
                    + "您于 " + btime + " 借阅的" + bname
                    + "已过截止日期，请您尽快还书！";
            sendSimpleMail(m.getEmail(), subject, content);
        }
    }

    // 预约提醒
    @Override
    public void processResReminder() {
        List<MailDetail> list = mailMapper.resReminder();
        for (MailDetail m : list) {
            String subject = "快来图书馆";
            String userName = m.getUserName();
            String bname = m.getBname(); //书名
            String content = m.getUserName() + ": "
                    + "您于" + m.getBtime() + "预约的" + "已经入库，您可前往图书馆借阅";
            sendSimpleMail(m.getEmail(), subject, content);
        }
    }
}
