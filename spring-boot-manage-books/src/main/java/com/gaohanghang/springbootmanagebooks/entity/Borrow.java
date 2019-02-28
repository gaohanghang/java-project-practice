package com.gaohanghang.springbootmanagebooks.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 13:41
 */
@Getter
@Setter
public class Borrow {
    private int borrowId; //借书id
    private int bookId; //书id
    private int userId; //用户id
    private Date btime; //借书时间
    private Date deadline; //规定归还时间
    private Date rtime; //归还时间
    private int operator; //操作者
}
