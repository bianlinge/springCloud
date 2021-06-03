package com.dove.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;
import java.io.Serializable;

public class ActiveMQ_Topic_Consumer2 {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
//        将ConnectionFactory改为ActiveMQConnectionFactory，并且设置信任所有包 不然会报错
        connectionFactory.setTrustAllPackages(true);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Topic mytopic = session.createTopic("mytopic");

        MessageConsumer consumer = session.createConsumer(mytopic);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                try {
                    User user = (User) ((ActiveMQObjectMessage) message).getObject();
                    System.out.println("收到的消息：" + user.toString());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
