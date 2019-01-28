package com.gaohanghang.mail.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/26 21:56
 */
public class OaEmail implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 接收人邮箱(过个逗号分开)
     */
    private String receiveEmail;

    /**
     * 主题
     */
    private String subject;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 模板
     */
    private String template;

    /**
     * 发送时间
     */
    private Timestamp sendTime;

    public OaEmail() {
        super();
    }

    public OaEmail(Long id, String receiveEmail, String subject, String content, String template, Timestamp sendTime) {
        this.id = id;
        this.receiveEmail = receiveEmail;
        this.subject = subject;
        this.content = content;
        this.template = template;
        this.sendTime = sendTime;
    }
}
