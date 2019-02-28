package com.gaohanghang.springbootmanagebooks.exception;

/**
 * @Description: 自定义预定异常
 * @author: Gao Hang Hang
 * @date 2019/02/28 12:43
 */
public class ReservationException extends RuntimeException {
    public ReservationException(String message) {
        super(message);
    }
}
