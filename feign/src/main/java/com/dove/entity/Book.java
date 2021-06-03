package com.dove.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Book {
    private String name;
    private String producer;
    private Date createTime;
    private Double price;
}
