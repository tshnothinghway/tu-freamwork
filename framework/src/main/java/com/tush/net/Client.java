/*
 * @(#) Client.java 2018-04-09
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.net;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tushenghong01
 * @version 2018-04-09
 */
@Slf4j
@Data
public class Client implements CallBack {

    private Server server;

    private volatile boolean check = true;

    private static final int CORE_POOL_SIZE = 1 << 3;

    private static final int MAX_POOL_SIZE = 1 << 4;

    private static final int ALIVE_TIME = 1 << 2;

    /**
     * 核心线程数 10
     * 最大线程数 20
     * 线程无工作时最大存活时间 60s
     * 自定义线程工厂
     * 拒绝策略为丢弃最早的任务
     *
     */
    ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, ALIVE_TIME,
                                                         TimeUnit.SECONDS, new ArrayBlockingQueue(10),
                                                         new CustomThreadFactory("test"),
                                                         new ThreadPoolExecutor.DiscardOldestPolicy());

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public void await() {
        lock.lock();
        try {
            log.info("睡觉");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 做什么
     */
    public void signal() {
        lock.lock();
        try {
            log.info("唤醒");
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public Client(Server server) {
        this.server = server;
    }

    public void doSomething() {
        await();
        server.print(this, "这是消息啊");
    }

    @Override
    public void process(String status) {
        log.info("你做了之后需要回调通知我");
    }

    public static void main(String[] args) {
        Client client = new Client(new Server());
        client.executor.execute(() -> client.doSomething());
        client.executor.execute(() -> client.signal());
    }
}
