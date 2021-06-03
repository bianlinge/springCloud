package com.dove.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Kafka_Consumer3 {
    public static void main(String[] args) {
        Properties props = new Properties();

        //设置kafka集群的地址
        props.put("bootstrap.servers", "192.168.198.128:9092;192.168.198.130:9092;192.168.198.131:9092");

        //设置消费者组，组名字自定义，组名字相同的消费者在一个组
//        props.put("group.id","myKafkaGroup_0");
        props.put("group.id", "myKafkaGroup_3");
        //开启offset自动提交
        props.put("enable.auto.commit", "false");
        //自动提交时间间隔
        props.put("auto.commit.interval.ms", "1000");
        //序列化器
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


      //实例化一个消费者
        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<Integer, String>(props);


        MyConsumerRebalanceListener listener = new MyConsumerRebalanceListener(consumer);
        //消费者订阅主题，可以订阅多个主题
        consumer.subscribe(Arrays.asList("mytopic"),listener);
        //死循环不停的从broker中拿数据
        try {
            while (true) {
                ConsumerRecords<Integer, String> records = consumer.poll(100000);

                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<Integer, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<Integer, String> partitionRecord : partitionRecords) {
                        System.out.println("partition: " + partitionRecord.partition() + " , "
                                + partitionRecord.offset() + ": " + partitionRecord.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                /*
                        可以将这里的偏移量提交挪到监听的onPartitionsRevoked方法中，控制灵活，但是也很容易出问题
                     */
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }

            }
        }finally {
            consumer.close();
        }

    }
}
