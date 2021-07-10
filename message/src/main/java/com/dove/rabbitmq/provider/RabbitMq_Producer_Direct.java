package com.dove.rabbitmq.provider;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMq_Producer_Direct {
    private static final String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.new 连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");//默认
        connectionFactory.setPassword("guest");//默认
        //2.创建 连接
        Connection connection = connectionFactory.newConnection();
        //3. 创建通道
        Channel channel = connection.createChannel();
        //4. 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //5. 定义发送消息
        String message = "新增一个订单";
        //6.  生产者发送消息时，设置消息的Routing Key:"insert"
        channel.basicPublish(EXCHANGE_NAME,"insert11212",null,message.getBytes());

        System.out.println("生产者发送消息：" + message);
        channel.close();
        connection.close();
    }
}
