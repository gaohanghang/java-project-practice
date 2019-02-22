package com.gaohanghang.mail.common.redis;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaohanghang.mail.common.model.Email;
import com.gaohanghang.mail.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 接收者
 * @author: Gao Hang Hang
 * @date 2019/01/31 16:50
 */
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private IMailService mailService;

    private CountDownLatch latch;

    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("接收email消息 <{}>", message);
        if (message == null) {
            LOGGER.info("接收email消息 <" + null + ">");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Email email = mapper.readValue(message, Email.class); //json转对象
                mailService.send(email);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }
}
