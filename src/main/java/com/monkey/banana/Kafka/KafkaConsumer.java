package com.monkey.banana.Kafka;


import com.monkey.banana.Controller.KafkaReceiveController;
import com.monkey.banana.Feed.TempListSaver;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumer extends Thread{
    private final ConsumerConnector consumer;
    private final String topic;

    public KafkaConsumer(String topic)
    {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                createConsumerConfig());
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig()
    {
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.zkConnect);
        props.put("group.id", KafkaProperties.groupId);
        props.put("zookeeper.session.timeout.ms", "60000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    // 这个东西可能会被用在web服务器上，用来接收大量信息
    @Override
    public void run() {
        KafkaReceiveController controller = new KafkaReceiveController();
        TempListSaver tempListSaver = new TempListSaver();

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        String kafkaData;
        System.out.println("[kafka收发器]此处为kafka消息接收器");

        while (it.hasNext()) {
            kafkaData = new String(it.next().message());
            System.out.println("receive：" + kafkaData);
            // kafka消费者的进程要把这个数据传送给生成临时列表的进程

            tempListSaver.invokeForCheck(kafkaData);
            // 错误代码
            //DeviceModel deviceModel = gson.fromJson(kafkaData, DeviceModel.class);
            //TempListSaver.deviceModels.add(deviceModel);


            // kafka消费者的进程要吧这个数据传送给通往数据库的控制器
            controller.getInfo(kafkaData);

            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
