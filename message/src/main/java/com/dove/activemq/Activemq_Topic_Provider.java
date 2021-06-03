package com.dove.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;

/**
 * Topic消息模式
 * pub/sub模式
 *
 * 此模式要保证消费者先行启动 生产者后启动 不然生产者不会在次发送
 */
public class Activemq_Topic_Provider {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();
        /**
         * 启动连接
         */
        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Destination topic = session.createTopic("mytopic");

        MessageProducer producer = session.createProducer(topic);


        //ObjectMessage，StreamMessage，MapMessage，BytesMessage string五种消息类型


        ActiveMQObjectMessage objectMessage = (ActiveMQObjectMessage) session.createObjectMessage();

        for (int i = 0; i < 10; i++) {
            User user = new User("root"+i, "root"+i);
            objectMessage.setObject(user);
            producer.send(objectMessage);
            System.out.println("topic 发出消息"+user.toString());
        }
        connection.close();
        session.close();
    }
}
