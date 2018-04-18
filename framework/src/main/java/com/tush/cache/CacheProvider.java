/*
 * @(#) CacheProvider.java 2018-04-18
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.cache;

import java.util.List;

/**
 * @author tushenghong01
 * @version 2018-04-18
 */
public interface CacheProvider {

    List<CacheBean> provider();
}
