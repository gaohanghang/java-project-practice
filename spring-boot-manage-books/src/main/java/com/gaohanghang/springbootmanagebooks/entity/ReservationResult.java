package com.gaohanghang.springbootmanagebooks.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 预定结果
 * @author: Gao Hang Hang
 * @date 2019/02/28 16:04
 */
@Getter
@Setter
public class ReservationResult<T> {
    boolean success;
    T data;
    String error;

    public ReservationResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public ReservationResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }
}
