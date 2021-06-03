package com.dove.web.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


public class CommonResult<T> {
    public int resCode=200;
    public T data;
    public String message="success";

    public int getResCode() {
        return this.resCode;
    }

    public void setResCode( int resCode) {
        this.resCode = resCode;
    }

    public CommonResult( int resCode,  T data,  String message) {
        this.resCode = resCode;
        this.data = data;
        this.message = message;
    }
    public CommonResult(T data) {
        this.data = data;
    }

    public CommonResult() {
    }

}
