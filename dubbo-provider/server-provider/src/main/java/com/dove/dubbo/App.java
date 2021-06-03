package com.dove.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

// xml方式启动
public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("META-INF/spring/dubboprovider.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
