package com.gaohanghang.springbootmanagebooks.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 13:47
 */
@Getter
@Setter
public class MailDetail {
    private String userName; //用户名
    private String email; //邮件地址
    private String bname; //书名
    private String btime; //预定时间
}
