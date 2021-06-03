package com.dove.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {
    @Autowired
    private KfkaProducer producer;

    @RequestMapping("/testSendMsg")
    @ResponseBody
    public String testSendMsg(){
        producer.send();
        return "success";
    }
}
