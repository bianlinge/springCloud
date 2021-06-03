package com.dove.pattern.adapter;

public class Result {
    private String code;
    private String msg;
    private String data;

    public String getData() {
        return this.data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }
}
