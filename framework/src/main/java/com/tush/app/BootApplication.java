package com.tush.app;/*
 * @(#) BootApplication.java 2018-04-04
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tushenghong01
 * @version 2018-04-04
 */
@SpringBootApplication
public class BootApplication {

    /**
     * @return
     */
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "tushenghong01@corp.netease.com";
    }

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
