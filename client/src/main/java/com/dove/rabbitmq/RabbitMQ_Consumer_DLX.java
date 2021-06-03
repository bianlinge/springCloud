package com.dove.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 死信队列：
 * 1. 消息被拒收
 * 2. 消息失效
 * 3. 消息过长
 * 4. 消息处理中发生业务错误
 * 转发到死信队列进一步处理处理
 */
public class RabbitMQ_Consumer_DLX {
    private static String EXCHANGE_DTL = "dlx_exchange";
    private static String QUEQE_DTL = "dlx_queue";

    private static String QUEQE_NORMAL = "normal_queue";
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
        //4. 声明DIRECT类型的死信交换机
        channel.exchangeDeclare(EXCHANGE_DTL, BuiltinExchangeType.DIRECT, true);
        //5. 声明TOPIC类型的正常交换机
        channel.exchangeDeclare(EXCHANGE_NORMAL, BuiltinExchangeType.TOPIC, true);
        //6. 声明正常队列. 构建
        Map<String, Object> arg = new HashMap<>();
        // 设置DLX
        arg.put("x-dead-letter-exchange",EXCHANGE_DTL);
        arg.put("x-dead-letter-routing-key","dlx");
        // 设置消息过期时间，消息过期后，会重新发布到DLX
        //arg.put("x-message-ttl", 5000);//5000ms
        channel.queueDeclare(QUEQE_NORMAL, true, false, false, arg);
        // 7.声明死信队列
        channel.queueDeclare(QUEQE_DTL, true, false, false, null);

        //8. 绑定队列和交换机
        channel.queueBind(QUEQE_NORMAL,EXCHANGE_NORMAL,"normal" );
        channel.queueBind(QUEQE_DTL,EXCHANGE_DTL,"dlx" );

        //9.消费消息
        //NORMAL  拒收后也会进入私信队列
        channel.basicConsume(QUEQE_NORMAL,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(final String consumerTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("拒收消息[deliveryTag]: "+deliveryTag);
               // channel.basicAck(deliveryTag,false);
               channel.basicNack(deliveryTag,false,false);//multiple:批量拒收  requeue 重新进入排队
                //channel.basicReject(deliveryTag,true);//true 重新排队就不去死信队列,//可能出现死循环 不停排队不停拒收
                channel.basicReject(deliveryTag,false);//false  消息拒收后也会进入私信队列

            }
        });

        channel.basicConsume(QUEQE_DTL,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(final String consumerTag, final Envelope envelope, final AMQP.BasicProperties properties, final byte[] body) throws IOException {
//                System.out.println("5s 后发送到死信队列 消费消息： "+new String(body));
                System.out.println("消息拒收后发送到死信队列 消费消息： "+new String(body));
            }
        });
    }
}
