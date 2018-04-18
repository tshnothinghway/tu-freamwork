/*
 * @(#) CacheRefresher.java 2018-04-18
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.cache;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * @author tushenghong01
 * @version 2018-04-18
 */
@Component
@Log4j2
public class CacheRefresher implements Refresher {

    private volatile boolean symbol;

    /**
     * 间隔时间为1s
     */
    private static final int DEFAULT_INTERVAL = 2 >> 1;

    /**
     * 默认四个线程
     */
    private static final int DEFAULT_THREADS_SIZE = 2 << 1;

    /**
     * 线程编号
     */
    private ThreadLocal<AtomicInteger> seq = ThreadLocal.withInitial(() -> new AtomicInteger(1));

    private ScheduledExecutorService exec = null;

    private int interval;

    @Autowired
    private CacheProvider cacheProvider;

    @Autowired
    private Cache<CacheBean> cache;

    public CacheRefresher() {
        this(DEFAULT_INTERVAL);
    }

    public CacheRefresher(int interval) {
        this.interval = interval;
    }

    @PostConstruct
    @Override
    public void init() {
        if (log.isInfoEnabled()) {
            log.info("cache container init starting ...");
        }
        //同步更新
        refresh();
        exec = new ScheduledThreadPoolExecutor(DEFAULT_THREADS_SIZE, new BasicThreadFactory.Builder().namingPattern(
                        "Cache-Thread-" + seq).daemon(true).build(), new ThreadPoolExecutor.DiscardOldestPolicy());
        if (log.isInfoEnabled()) {
            log.info("cache container init succeed...");
        }

        if (exec instanceof ScheduledThreadPoolExecutor) {
            ((ScheduledThreadPoolExecutor) this.exec).setRemoveOnCancelPolicy(true);
        }
        scheduled();
    }

    @Override
    public void scheduled() {
        Runnable task = () -> refresh();
        while (symbol) {
            exec.scheduleWithFixedDelay(task, interval, interval, TimeUnit.MINUTES);
        }
    }

    @Override
    public void refresh() {
        List<CacheBean> cacheBeanList = cacheProvider.provider();
        cacheBeanList.forEach(cacheBean -> cache.set(cacheBean));
    }

    @Override
    public void destroy() {
        cache.destroy();
        symbol = false;
    }
}
