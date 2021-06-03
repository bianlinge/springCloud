package com.dove.zk;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/helloworld")
    public JSONObject HelloWorld() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "Hello World!");
        return jsonObject;
    }
}
