package com.dove.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJms//启动消息队列
public class ActivemqApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }
}
