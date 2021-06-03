package com.dove.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * kafka 生产者客户端 发送消息到kafka
 */
public class Kafka_Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //设置kafka集群的地址
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.198.128:9092");
          properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                  "192.168.198.128:9092;192.168.198.130:9092;192.168.198.131:9092");

        /*//ack模式，all是最慢但最安全的
         0 : 消息发送到broker 不需要确认，效率高 不安全 可能会丢失消息
         1 : 只需要获得leader 节点确认即可返回
         -1(all) ：所以节点都需要进行确认 效率低 安全 单当节点只有一个的时候也会出现消息丢失情况
        */
        properties.put(ProducerConfig.ACKS_CONFIG, "-1");
        //失败重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);

        //每个分区未发送消息总字节大小（单位：字节），超过设置的值会提交数据到服务端
        //properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10);
        //批量发送的大小 没满10条刷入到partition
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 10);
        //消息在缓冲区保留的时间，超过设置的值就会被提交到服务端
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 0);

        //序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);

        for (int i = 0; i < 50; i++) {
            //new ProducerRecord<Integer, String>(topic,key,value)
            producer.send(new ProducerRecord<Integer, String>("mytopic", i,"hello: "+i));
            System.out.println("发送消息："+"hello: "+i);
        }
        producer.close();
    }
}
