package com.gaohanghang.springbootmanagebooks.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:00
 */
@Getter
@Setter
@ToString
public class ReservationDetail {
    private int reservationId;
    private int bookId;
    private String bname;
    private Date deadline;
    private String userName;
}
