package com.dove.web;

import com.dove.web.config.CommonResult;
import com.dove.web.config.DemoObj;
import com.dove.web.config.Dogg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @RequestMapping("/test/{id}")
    public String test(@PathVariable("id") String id) {
        return id;
    }

    @RequestMapping("/testobj")
    public DemoObj test2(@RequestBody DemoObj obj) {
        return obj;
    }

    @RequestMapping("/dog")
    public Dogg dog(@RequestBody Dogg dogg) {
        return dogg;
    }

    @RequestMapping("/")
    public String huashenggke() {
        return "huashengke";
    }
}
