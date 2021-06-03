package com.dove.web.config;

import lombok.Data;

@Data
public class DemoObj {
    private String id;

    private String name;

    public DemoObj(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
