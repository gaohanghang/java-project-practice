package com.imooc.kafka.controller;

import com.google.gson.Gson;
import com.imooc.kafka.common.ErrorCode;
import com.imooc.kafka.common.MessageEntity;
import com.imooc.kafka.common.Response;
import com.imooc.kafka.producer.SimpleProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class ProduceController {

    @Autowired
    private SimpleProducer simpleProducer;

    @Value("${kafka.topic.default}")
    private String topic;

    private Gson gson = new Gson();

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = {"application/json"})
    public Response sendKafka() {
        return new Response(ErrorCode.SUCCESS, "OK");
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = {"application/json"})
    public Response sendKafka(@RequestBody MessageEntity message) {
        try {
            log.info("kafka的消息={}", gson.toJson(message));
            simpleProducer.send(topic, "key", message);
            log.info("发送kafka成功");
            return new Response(ErrorCode.SUCCESS, "发送Kafka成功");
        } catch (Exception e) {
            log.error("发送kafka失败", e);
            return new Response(ErrorCode.EXCEPTION, "发送kafka失败");
        }
    }
}
