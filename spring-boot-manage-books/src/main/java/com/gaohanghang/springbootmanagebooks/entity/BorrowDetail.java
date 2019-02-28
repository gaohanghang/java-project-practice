package com.gaohanghang.springbootmanagebooks.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 13:42
 */
@Getter
@Setter
public class BorrowDetail {
    private int borrowId; //借书id
    private String bname; //书名
    private String userName; //用户名
    private Date btime; //借书时间
    private Date deadline; //规定归还时间
    private Date rtime; //归还时间
}
