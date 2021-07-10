package com.dove.activemq.provider;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Activemq_Queue_Provider {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://127.0.0.1:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
//        paramA是设置事务的，paramB设置acknowledgment mode
//        paramA设置为false时：paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
//        paramA设置为true时： paramB的值忽略， acknowledgment mode被jms服务器设置为SESSION_TRANSACTED 。
//        Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。
//        Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会删除消息。
//        DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("myqueue");
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("hello world"+ i);
            producer.send(textMessage);
            System.out.println("发送消息：Activemq 发送消息" + i);
           // session.commit(); 事务型消息 需要commit
        }
        session.close();
        connection.close();
    }
}
