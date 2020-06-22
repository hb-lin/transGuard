package com.tansun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tansun.util.FileUtil;
import com.tansun.util.KafkaUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author linhb
 * @Date 2019/12/20
 **/
@RestController
@RequestMapping(value = "")
public class KafkaContoller {

    @RequestMapping(value = "/dmgMdQuery/query/catalog/tableColumn/list/full/{id}", method = RequestMethod.POST)
    public String SearchListColumn(@RequestBody JSONObject jsonParam) {
        System.out.println("获取kafka字段列表——request参数：" + jsonParam.toJSONString());
//        jsonParam.getJSONObject("dataType");
        String result = FileUtil.readJson("kafka/column.json");
        return result;
    }

    @RequestMapping(value = "/dmgMdQuery/ds/source/physicalInfo/{id}", method = RequestMethod.POST)
    public String getConfig(@RequestBody JSONObject jsonParam,HttpServletRequest request) {
        System.out.println("获取kafka配置——request参数：" + jsonParam.toJSONString());
        System.out.println("X-AUTH-APP:"+request.getHeader("X-AUTH-APP"));
        System.out.println("X-AUTH-TOKEN:"+request.getHeader("X-AUTH-TOKEN"));
        String result = FileUtil.readJson("kafka/physicalInfo.json");
        return result;
    }

    @RequestMapping(value = "/dmgMdQuery/query/table/list/full/{id}", method = RequestMethod.POST)
    public String getTopicName(HttpServletRequest request,@RequestBody JSONObject jsonParam) {
        System.out.println("获取TopicRealName——request参数：" + jsonParam.toJSONString());
//        jsonParam.getJSONObject("dataType");
        String result = FileUtil.readJson("kafka/realTopic.json");
        return result;
    }

    @RequestMapping(value = "/auth/key", method = RequestMethod.GET)
    public String getCode() {
        return "2019042619002700";
    }

    public static void main(String[] args) {
       /* KafkaProducer<String, String> producer = KafkaUtil.getKafkaProducer("127.0.0.1:9092");

        try {
            ProducerRecord<String, String> record = new ProducerRecord<>("test", "1", "testlinhb");
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("123123123");*/
        List<String> dataList = new ArrayList<>();
        KafkaConsumer<String,String> consumer = KafkaUtil.getKafkaConsumer("127.0.0.1:9092");
        consumer.subscribe(Arrays.asList("test"));
        dataList.clear();
        while (true){
            ConsumerRecords<String,String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("接收fetched from partition "+record.partition()
                        +",offset:"+record.offset()+",message:"+record.value());
                dataList.add(record.value());
            }
        }
    }

}
