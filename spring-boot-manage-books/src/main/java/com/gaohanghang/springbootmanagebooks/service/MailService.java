package com.gaohanghang.springbootmanagebooks.service;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:51
 */
public interface MailService {
    // 发送简单邮件
    void sendSimpleMail(String to, String subject, String content);
    // 归还提醒
    void processReturnReminder();
    // 预定提醒
    void processResReminder();
}
