package com.dove.web.config;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ConverterController {
    @RequestMapping(value = "/converter", produces = { "application/x-dove" })
    public @ResponseBody  DemoObj convert(@RequestBody DemoObj demoObj) {

        System.out.println("===========converter============");
        return demoObj;

    }
}
