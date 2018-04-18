package com.tush.kafka;/*
 * @(#) Message.java 2018-04-08
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

import java.util.Date;

import lombok.Data;

/**
 * @author tushenghong01
 * @version 2018-04-08
 */
@Data
public class Message {

    /**
     *  id
     */
    private Long id;

    /**
     * 消息
     */
    private String msg;

    /**
     * 时间戳
     */
    private Date sendTime;
}
