/*
 * @(#) ThreadFactory.java 2018-04-09
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.net;

import java.util.concurrent.ThreadFactory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tushenghong01
 * @version 2018-04-09
 */
@Data
@Slf4j
public class CustomThreadFactory implements ThreadFactory {

    private String threadName;

    public CustomThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(false);
        thread.setName(threadName + "-thread");
        return thread;
    }
}
