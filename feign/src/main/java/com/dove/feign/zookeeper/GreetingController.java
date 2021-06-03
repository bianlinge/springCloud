package com.dove.feign.zookeeper;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    private HelloWorldClient helloWorldClient;

    @GetMapping("/get-greeting")
    public JSONObject greeting() {

        return helloWorldClient.HelloWorld();

    }
}
