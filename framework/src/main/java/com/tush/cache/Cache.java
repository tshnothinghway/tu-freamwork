/*
 * @(#) Cache.java 2018-04-18
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.cache;

/**
 * @author tushenghong01
 * @version 2018-04-18
 */
public interface Cache<T> {

    /**
     * 添加缓存
     * @param t
     * @return
     */
    boolean set(T t);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    T get(String key);

    /**
     * 判断值是否存在
     * @param key
     * @return
     */
    boolean isLegal(String key);

    /**
     * 清除缓存
     */
    void destroy();

}
