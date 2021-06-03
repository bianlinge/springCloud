package com.dove.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 注意：exchange与队列一样都需要提前声明，如果未声明就使用交换机，则会报错。
 * 如果不清楚生产者和消费者谁先声明，为了保证不报错，
 * 生产者和消费者都声明交换机，同样的，交换机的创建也会保证幂等性。
 */
public class RabbitMQ_Consumer_Fanout1 {

    private static final String QUEUE_NAME = "fanout_queue_1";
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //消费者声明自己的队列
        channel.queueDeclare(QUEUE_NAME, false, false,false, null);

        //消费者声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        //消费真 队列和交换机进行绑定
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"");

        //消费消息
        channel.basicConsume(QUEUE_NAME,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(final String consumerTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
                System.out.println("广播模式收到消息： "+new String(body));
            }
        });




    }

}
