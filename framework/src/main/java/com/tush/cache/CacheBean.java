/*
 * @(#) CacheBean.java 2018-04-18
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.tush.cache;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tushenghong01
 * @version 2018-04-18
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CacheBean implements Serializable {
    private static final long serialVersionUID = -6202859100001156576L;
    /**
     * 主键
     */
    private Long id;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;

}
