package com.dove.kafka;

import io.swagger.models.auth.In;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Arrays;
import java.util.Properties;

public class Kafka_Consumer {
    public static void main(String[] args) {
        Properties props = new Properties();

        //设置kafka集群的地址
        props.put("bootstrap.servers", "192.168.198.128:9092");

        //设置消费者组，组名字自定义，组名字相同的消费者在一个组
//        props.put("group.id","myKafkaGroup_0");
        props.put("group.id","myKafkaGroup_1");
        //开启offset自动提交
        props.put("enable.auto.commit", "true");
        //自动提交时间间隔
        props.put("auto.commit.interval.ms", "1000");
        //序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        /*消费的offset起始点
        * earliest: 对于新的group.id 重置offset
        * latest: 对于新的group.id 取以提交消费后的最大offset
        * none : 没有offset报错
        * */
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        //实例化一个消费者
        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<Integer, String>(props);

        //消费者订阅主题，可以订阅多个主题
        consumer.subscribe(Arrays.asList("mytopic"));
        //死循环不停的从broker中拿数据
        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(10000);
            for (ConsumerRecord<Integer, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n",
                        record.offset(),
                        record.key(),
                        record.value());
            }
        }

    }
}
