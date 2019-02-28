package com.gaohanghang.springbootmanagebooks.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 12:43
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book implements Serializable {
    private int bookId;
    private String ISBN;
    private String location;
    private int state;
    private int operator;
}
