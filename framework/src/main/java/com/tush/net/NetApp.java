/*
 * @(#) NetTest.java 2018-04-09
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tushenghong01
 * @version 2018-04-09
 */
@Slf4j
public class NetApp {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();

        log.info("localhost {}", localHost);
    }
}
