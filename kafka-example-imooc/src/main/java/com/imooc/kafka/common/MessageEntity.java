package com.imooc.kafka.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class MessageEntity {
    private String title;
    private String body;

    @Override
    public String toString() {
        return "MessageEntity{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
