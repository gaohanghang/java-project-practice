package com.gaohanghang.messageboard.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:50
 */
@Entity
public class Word {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "Int(11) COMMENT '留言主键'")
    private int wordId;
    @Column(nullable = false, columnDefinition = "Int(11) COMMENT '用户外键'")
    private int userId;
    @Column(nullable = false, columnDefinition = "varchar(100) COMMENT '留言标题' default '未命名'")
    private String title;
    @Column(nullable = false, columnDefinition = "varchar(20000) COMMENT '留言内容' default '没有内容'")
    private String content;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "datetime COMMENT '留言时间'")
    private Timestamp leaveTime;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }
}