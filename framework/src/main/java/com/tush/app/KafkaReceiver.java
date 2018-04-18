package com.tush.app;/*
 * @(#) KafkaReceiver.java 2018-04-08
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tushenghong01
 * @version 2018-04-08
 */
@Service("KafkaReceiver")
@Slf4j
public class KafkaReceiver {

    @KafkaListener(topics = {"tush"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }
    }
}
