/*
 * @(#) CSCallBack.java 2018-04-09
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.net;

/**
 * @author tushenghong01
 * @version 2018-04-09
 */
public interface CallBack {

    /**
     * 回调任务
     * @param status
     * @Return
     */
    void process(String status);
}
