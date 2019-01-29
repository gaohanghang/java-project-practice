package com.gaohanghang.mail.service;

import com.gaohanghang.mail.common.model.Email;
import com.gaohanghang.mail.common.model.Result;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/29 11:46
 */
public interface IMailService {
    /**
     * 纯文本
     * @param email
     * @throws Exception
     */
    public void send(Email email) throws Exception;

    /**
     * 富文本
     * @param mail
     * @throws Exception
     */
    public void sendHtml(Email mail) throws Exception;

    /**
     * 模版发送 freemarker
     * @param mail
     * @throws Exception
     */
    public void sendFreemarker(Email mail) throws Exception;

    /**
     * 模版发送 thymeleaf(弃用、需要配合模板)
     * @param mail
     * @throws Exception
     */
    public void sendThymeleaf(Email mail) throws Exception;

    /**
     * 队列
     * @param mail
     * @throws Exception
     */
    public void sendQueue(Email mail) throws Exception;

    /**
     * Redis 队列
     * @param mail
     * @throws Exception
     */
    public void sendRedisQueue(Email mail) throws Exception;

    public Result listMail(Email mail);
}
