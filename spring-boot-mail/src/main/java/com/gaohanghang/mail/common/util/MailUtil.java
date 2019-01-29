package com.gaohanghang.mail.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步发送
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/29 12:20
 */
public class MailUtil {
    // 统计
    private final AtomicInteger count = new AtomicInteger(1);
    private Logger logger = LoggerFactory.getLogger(MailUtil.class);
    private ScheduledExecutorService service = Executors.newScheduledThreadPool(6);



}
