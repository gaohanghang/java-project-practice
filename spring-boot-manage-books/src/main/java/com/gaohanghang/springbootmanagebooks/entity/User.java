package com.gaohanghang.springbootmanagebooks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:07
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private int userId;
    private String userName;
    private String passWd;
    private String rname;
    private String phone;
    private String email;

    public User(String userName, String passWd) {
        this.userName = userName;
        this.passWd = passWd;
    }
}
