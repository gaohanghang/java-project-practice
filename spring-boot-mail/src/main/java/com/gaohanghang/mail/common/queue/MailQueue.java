package com.gaohanghang.mail.common.queue;

import com.gaohanghang.mail.common.model.Email;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: 邮件队列
 * @author: Gao Hang Hang
 * @date 2019/01/29 01:05
 */
public class MailQueue {
    // 队列项目
    static final int QUEUE_MAX_SIZE = 1000;

    static BlockingDeque<Email> blockingDeque = new LinkedBlockingDeque<>();

    /**
     * 私有的默认构造器，保证外界无法直接实例化
     */
    private MailQueue() {}

    // 单例队列
    public static MailQueue getMailQueue() {
        return SingletonHolder.queue;
    }

    // 生成入队
    public void produce(Email mail) throws InterruptedException {
        blockingDeque.put(mail);
    }

    // 消费出队
    public Email consume() throws InterruptedException {
        return blockingDeque.take();
    }

    // 获取队列大小
    public int size() {
        return blockingDeque.size();
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static MailQueue queue = new MailQueue();
    }
}
