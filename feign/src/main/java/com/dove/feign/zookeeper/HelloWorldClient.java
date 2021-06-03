package com.dove.feign.zookeeper;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class HelloWorldClient {
    @Autowired
    private TheClient theClient;


    @FeignClient(name = "HelloWorld")
    interface TheClient {
        @RequestMapping(path = "/helloworld", method = RequestMethod.GET)
        @ResponseBody
        JSONObject HelloWorld();
    }

    public JSONObject HelloWorld() {
        return theClient.HelloWorld();
    }
}
