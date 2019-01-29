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
 * @date 2019/01/25 10:49
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false, columnDefinition = "Int(11) COMMENT '用户主键'")
    private int userId;
    @Column(nullable = false, columnDefinition = "varchar(40) COMMENT '用户名' default '未命名'")
    private String userName;
    @Column(nullable = false, columnDefinition = "varchar(40) COMMENT '用户密码' default ' '")
    private String userPassword;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false, columnDefinition = "datetime COMMENT '注册时间'")
    private Timestamp registerTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }
}