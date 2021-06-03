package com.dove.multithread;

public class Request {
    private String name;

    public Request(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
