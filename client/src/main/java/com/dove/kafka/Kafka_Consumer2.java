package com.dove.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Kafka_Consumer2 {
    public static void main(String[] args) {
        Properties props = new Properties();

        //设置kafka集群的地址
//        props.put("bootstrap.servers", "192.168.198.128:9092");
        props.put("bootstrap.servers", "192.168.198.128:9092;192.168.198.130:9092;192.168.198.131:9092");
        //设置消费者组，组名字自定义，组名字相同的消费者在一个组
//        props.put("group.id","myKafkaGroup_0");
        props.put("group.id","myKafkaGroup_2");
        //开启offset自动提交
        props.put("enable.auto.commit", "true");
        //自动提交时间间隔
        props.put("auto.commit.interval.ms", "1000");
        //序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"none");Exception in thread "main" org.apache.kafka.clients.consumer.NoOffsetForPartitionException: Undefined offset with no reset policy for partitions: [mytopic-0]
        //实例化一个消费者
        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<Integer, String>(props);

        //消费者订阅主题，可以订阅多个主题
        consumer.subscribe(Arrays.asList("mytopic"));
        //死循环不停的从broker中拿数据
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(100000);
            for (ConsumerRecord<Integer, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n",
                        record.offset(),
                        record.key(),
                        record.value());
            }
        }

    }
}
