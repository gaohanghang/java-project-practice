package com.gaohanghang.mail.service.impl;

import com.gaohanghang.mail.common.model.Email;
import com.gaohanghang.mail.common.model.Result;
import com.gaohanghang.mail.common.queue.MailQueue;
import com.gaohanghang.mail.entity.OaEmail;
import com.gaohanghang.mail.repository.MailRepository;
import com.gaohanghang.mail.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/29 11:46
 */
@Service
@Component
public class MailServiceImpl implements IMailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private MailRepository mailRepository;




    @Override
    public void send(Email email) throws Exception {

    }

    @Override
    public void sendHtml(Email mail) throws Exception {

    }

    @Override
    public void sendFreemarker(Email mail) throws Exception {

    }

    @Override
    public void sendThymeleaf(Email mail) throws Exception {

    }

    @Override
    public void sendQueue(Email mail) throws Exception {
        MailQueue.getMailQueue().produce(mail);
    }

    @Override
    public void sendRedisQueue(Email mail) throws Exception {

    }

    @Override
    public Result listMail(Email mail) {
        List<OaEmail> list = mailRepository.findAll();
        return Result.ok(list);
    }
}
