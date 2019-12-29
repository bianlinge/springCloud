package com.imodule.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
   @Autowired
    HelloWorldService helloWorldService;
    @GetMapping("/hello")
    public String sayHello(){
        return helloWorldService.sayHello();
    }
}
