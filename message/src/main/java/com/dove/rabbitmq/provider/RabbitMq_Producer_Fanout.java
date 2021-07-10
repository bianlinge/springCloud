package com.dove.rabbitmq.provider;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class RabbitMq_Producer_Fanout {
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        String message = "hello world";

        //广播模式是将消息发送给交换机，交换机根据路由将消息路由到指定的队列
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("生产者发送消息：" + message);
        channel.close();
        connection.close();

    }
}
