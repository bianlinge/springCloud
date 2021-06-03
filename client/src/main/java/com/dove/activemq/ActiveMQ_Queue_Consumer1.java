package com.dove.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;

public class ActiveMQ_Queue_Consumer1 {
    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

        Destination myqueue = session.createQueue("myqueue");
        MessageConsumer consumer = session.createConsumer(myqueue);


//        使用while (true) 死循环来不停接受消息。这样很浪费cpu资源，实际生产中不会这么做。下面，我们采用注册一个监听器的方法
        /*try {
            while (true) {
                TextMessage receiveMsg = (TextMessage) consumer.receive(1000 * 100);
                if (receiveMsg != null) {
                    System.out.println("收到的消息:" + receiveMsg.getText());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session!=null) {
                session.close();
            }
            if (connection!=null) {
                connection.close();
            }
        }*/

        //监听器

        try {
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("收到的消息：" + textMessage.getText());
                        message.acknowledge();//Session.CLIENT_ACKNOWLEDGE 需要手动ack  告诉mq 删除以消费的消息
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
