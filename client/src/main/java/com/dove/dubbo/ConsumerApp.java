package com.dove.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ConsumerApp {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
//        ProviderService providerService = (ProviderService) context.getBean("providerService");
        ProviderService providerService = context.getBean(ProviderService.class);
        String dove = providerService.sasyHello("dove");
        System.out.println(dove);
        System.in.read();
    }
}
