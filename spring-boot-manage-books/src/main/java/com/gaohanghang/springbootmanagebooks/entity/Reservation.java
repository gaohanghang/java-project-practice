package com.gaohanghang.springbootmanagebooks.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: 预定类
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:00
 */
@Getter
@Setter
@ToString
public class Reservation {
    private int reservationId;
    private int userId;
    private int bookId;
    private Date deadline;
}
