package com.gaohanghang.mail.common.queue;

import com.gaohanghang.mail.common.model.Email;
import com.gaohanghang.mail.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: 消息队列
 * @author: Gao Hang Hang
 * @date 2019/01/29 01:02
 */
public class ConsumeMailQueue {

    // 队列项目
    static final int QUEUE_MAX_SIZE = 1000;
    private static final Logger logger = LoggerFactory.getLogger(ConsumeMailQueue.class);
    static BlockingDeque<Email> blockingDeque = new LinkedBlockingDeque<>();
    @Autowired
    IMailService mailService;

    /**
     * 私有的默认构造器，保证外界无法直接实例化
     */
    private ConsumeMailQueue() {
    }

    @PostConstruct
    public void startThread() {
        ExecutorService e = Executors.newFixedThreadPool(2); // 两个大小的固定线程池
    }

    class PollMail implements Runnable {
        IMailService mailService;

        public PollMail(IMailService mailService) {
            this.mailService = mailService;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Email mail = MailQueue.getMailQueue().consume();
                    if (mail != null) {
                        logger.info("剩余邮件总数:{}", MailQueue.getMailQueue().size());
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
