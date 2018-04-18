/*
 * @(#) Refresher.java 2018-04-18
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.cache;

/**
 * @author tushenghong01
 * @version 2018-04-18
 */
public interface Refresher {

    void init();

    void scheduled();

    void refresh();

    void destroy();
}
