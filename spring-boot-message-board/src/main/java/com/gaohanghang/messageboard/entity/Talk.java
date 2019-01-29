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
 * @date 2019/01/25 19:50
 */
@Entity
public class Talk {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "Int(11) COMMENT '留言主键'")
    private int talkId;
    @Column(nullable = false, columnDefinition = "Int(11) COMMENT '用户外键'")
    private int userId;
    @Column(nullable = false, columnDefinition = "varchar(20000) COMMENT '留言内容' default '没有内容'")
    private String content;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "datetime COMMENT '留言时间'")
    private Timestamp leaveTime;

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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