/*
 * @(#) CacheManager.java 2018-04-18
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.cache;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * @author tushenghong01
 * @version 2018-04-18
 */
@Component
public class CacheManager implements Cache<CacheBean> {

    private static Map<String, CacheBean> CACHE_MAP = Maps.newConcurrentMap();

    private static Map<Long, String> ID_KEY_MAP = Maps.newConcurrentMap();

    @Override
    public boolean set(CacheBean cacheBean) {
        if (cacheBean == null) {
            return false;
        }
        CACHE_MAP.put(cacheBean.getKey(), cacheBean);
        ID_KEY_MAP.put(cacheBean.getId(), cacheBean.getKey());
        return true;
    }

    @Override
    public CacheBean get(String key) {
        return CACHE_MAP.get(key);
    }

    @Override
    public boolean isLegal(String key) {
        return CACHE_MAP.get(key) == null ? false : true;
    }

    @Override
    public void destroy() {

    }
}
