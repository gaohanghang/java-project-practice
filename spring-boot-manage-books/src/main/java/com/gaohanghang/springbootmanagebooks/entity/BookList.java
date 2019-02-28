package com.gaohanghang.springbootmanagebooks.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 13:35
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookList {
    private String isbn; //国际标准书号
    private String bname;
    private String publisher; //出版者
    private String writer; //作者
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date ptime;
    private int number; //编号
    private int operator; //操作者
}
