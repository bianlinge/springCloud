package com.dove.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ_Consumer_Direct2 {

    private static final String EXCHANGE_NAME = "direct_exchange";
    private static final String QUEUE_NAME = "direct_queue_2";

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
        //5. 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //6. 使用路由key 绑定交换机和队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "insert222");
        //7. 消费消息
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(final String consumerTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
                System.out.println("路由insert222接受消息： " + new String(body));
            }
        });
    }
}
