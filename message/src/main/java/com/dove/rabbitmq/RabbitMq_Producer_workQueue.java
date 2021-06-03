package com.dove.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMq_Producer_workQueue {
    private static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setPassword("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        //Queue.DeclareOk queueDeclare(String queue,
        //                              boolean durable(持久化),
        //                              boolean exclusive, (独占连接)
        //                              boolean autoDelete,（自动删除队列）
        //                              Map<String, Object> arguments) throws IOException;
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 20; i++) {
            String message = "Hello World!" + i;
            System.out.println("rabbit publish>>>>> "+ message);
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            Thread.sleep(500);
        }

        channel.close();
        connection.close();
    }
}
