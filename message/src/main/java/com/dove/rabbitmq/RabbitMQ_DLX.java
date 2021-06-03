package com.dove.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ_DLX {
    private static String EXCHANGE_NORMAL = "normal_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.new 连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");//默认
        connectionFactory.setPassword("guest");//默认
        //2.创建 连接
        Connection connection = connectionFactory.newConnection();
        //3. 创建通道
        Channel channel = connection.createChannel();
        //4. 声明TOPIC类型的正常交换机
        channel.exchangeDeclare(EXCHANGE_NORMAL, BuiltinExchangeType.TOPIC, true);
        String message = "hello world!!!!!!!!";

        channel.basicPublish(EXCHANGE_NORMAL, "normal", null, message.getBytes());
        channel.close();
        connection.close();
        System.out.println("延迟消息测试");
    }
}
