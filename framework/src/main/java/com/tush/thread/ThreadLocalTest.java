/*
 * @(#) ThreadLocal.java 2018-04-10
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.thread;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tushenghong01
 * @version 2018-04-10
 */
@Slf4j
public class ThreadLocalTest {

    static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "123");

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            threadLocal.set("1234");
            log.info("thread-1234:{}", threadLocal.get());
        }).start();
        TimeUnit.SECONDS.sleep(5);
        log.info("thread-123 : {}", threadLocal.get());
    }

}
