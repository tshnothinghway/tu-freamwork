/*
 * @(#) GenericTestImpl.java 2018-04-09
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.net;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tushenghong01
 * @version 2018-04-09
 */
@Slf4j
@Data
@EqualsAndHashCode()
public class GenericTestImpl implements GenericTest {

    private volatile boolean check = true;

    private static final int CORE_POOL_SIZE = 1 << 3;

    private static final int MAX_POOL_SIZE = 1 << 4;

    private static final int ALIVE_TIME = 60;

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

    @Override
    public void reload() {

        /**
         * check为真的时候执行线程
         */
        while (check) {
            try {
                for (int i = 0; i < MAX_POOL_SIZE; i++) {
                    int num = i;
                    executor.execute(() -> log.info("i m work {}", num));
                    if (i == MAX_POOL_SIZE >> 2) {
                        check = false;
                        return;
                    }
                }

            } catch (Exception e) {
                log.error("thread has catch a exception {}", e);
            }

        }

    }

    public static void main(String[] args) {
        GenericTest test = new GenericTestImpl();

        test.reload();
    }
}
