package com.gaohanghang.messageboard.utils;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:55
 */
public class Response {
    private String status;
    private Object content;

    public Response() {
    }

    public Response(String status, Object content) {
        this.status = status;
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}