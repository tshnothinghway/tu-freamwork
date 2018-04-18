/*
 * @(#) Server.java 2018-04-09
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.net;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tushenghong01
 * @version 2018-04-09
 */
@Slf4j
public class Server {

    /**
     * @param callBack
     * @param msg
     */
    public void print(CallBack callBack, String msg) {
        log.info("服务端：服务端接收到客户端发送的消息为:{}", msg);

        //模拟服务端需要对数据处理
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            log.error("InterruptedException :{}", e);
        }
        log.info("服务端:数据处理成功，返回成功状态 200");
        String status = "200";
        callBack.process(status);
    }
}
