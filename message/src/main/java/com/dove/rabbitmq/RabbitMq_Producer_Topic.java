package com.dove.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMq_Producer_Topic {
    private static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.new 连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");//默认
        connectionFactory.setPassword("guest");//默认
        //2.创建 连接
        Connection connection = connectionFactory.newConnection();
        //3. 创建通道
        Channel channel = connection.createChannel();
        //4. 声明topic 类型交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //5. 定义发送消息
        String message = "hello rabbitMQ";
        //6.  生产者发送消息时，设置消息的 topic Routing Key
        //#：匹配一个或多个词 >>>>>    audit.#：能够匹配audit.irs.corporate 或者 audit.irs
        //*：匹配不多不少恰好1个词>>>>  audit.*：只能匹配audit.irs
        channel.basicPublish(EXCHANGE_NAME, "audit.irs.corporate", null, message.getBytes());
//        channel.basicPublish(EXCHANGE_NAME, "audit.irs", null, message.getBytes());
        System.out.println("topic 生成者发送消息： " + message);

        channel.close();
        connection.close();
    }
}
