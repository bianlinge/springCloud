package com.dove.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQObjectMessage;

import javax.jms.*;
import java.io.Serializable;

public class ActiveMQ_Topic_Consumer1 {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //javax.jms.JMSException: Failed to build body from content.
        // Serializable class not available to broker.
        // Reason: java.lang.ClassNotFoundException: Forbidden class com.dove.activemq.User!
        // This class is not trusted to be serialized as ObjectMessage payload.
        // Please take a look at http://activemq.apache.org/objectmessage.html for more information on how to configure trusted classes.


//        将ConnectionFactory改为ActiveMQConnectionFactory，并且设置信任所有包
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
