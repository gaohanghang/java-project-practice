package com.imooc.kafka.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int code;
    private String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
