package com.tansun.util;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author linhb
 * @Date 2019/12/24
 **/
public class KafkaUtil {
    private static KafkaProducer<String, String> kafkaProducer;
    private static Map<String, KafkaProducer<String, String>> kafkaProducerMap = new HashMap<>();
    private static Map<String, KafkaConsumer<String, String>> kafkaConsumerMap = new HashMap<>();
    private static String brokerlist;

    /**
     * 获取kafka生产者
     *
     * @return
     */
    public static KafkaProducer<String, String> getKafkaProducer() {
        if (kafkaProducer == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", brokerlist);
            props.put("acks", "0");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            kafkaProducer = new KafkaProducer<String, String>(props);
        }
        return kafkaProducer;
    }

    /**
     * 获取kafka生产者
     *
     * @param brokerList
     * @return
     */
    public static KafkaProducer<String, String> getKafkaProducer(String brokerList) {
        KafkaProducer<String, String> kafkaProducer = kafkaProducerMap.get(brokerList);
        if (kafkaProducer == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", brokerList);
            props.put("acks", "0");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            kafkaProducer = new KafkaProducer<String, String>(props);
            kafkaProducerMap.put(brokerList, kafkaProducer);
        }
        return kafkaProducer;
    }

    public static KafkaConsumer<String, String> getKafkaConsumer(String brokerList) {
        KafkaConsumer<String, String> kafkaConsumer = kafkaConsumerMap.get(brokerList);
        if (kafkaConsumer == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", brokerList);
            props.put("group.id", "11");
            props.put("acks", "0");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            kafkaConsumer = new KafkaConsumer<String, String>(props);
            kafkaConsumerMap.put(brokerList, kafkaConsumer);
        }
        return kafkaConsumer;
    }
}
