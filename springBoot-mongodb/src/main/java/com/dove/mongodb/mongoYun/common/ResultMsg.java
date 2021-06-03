package com.dove.mongodb.mongoYun.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ResultMsg<T> {
    public int code;
    public String msg;
    public Object data;

    public ResultMsg() {
    }

    public ResultMsg(final int code, final String msg, final Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
