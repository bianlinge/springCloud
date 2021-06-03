package com.dove.mongodb.mongoYun.entity;

import lombok.Data;

@Data
public class Progress {
    private long received = 0;
    private long size = 0;
    private long finish = 0;
}
