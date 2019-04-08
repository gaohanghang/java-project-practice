package com.gaohanghang.springbootgephi.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/28 15:36
 */
@Entity
@Table(name = "relationship")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Relationship extends BaseRowModel {

    @Id
    private String id;
    @ExcelProperty(index = 0)
    private String topic;
    @ExcelProperty(index = 1)
    private String sender;
    @ExcelProperty(index = 2)
    private String receiver;
    @ExcelProperty(index = 3, format = "yyyy/MM/dd HH:mm:ss")
    private Date beginTime;
    @CreatedDate
    private LocalDateTime createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
