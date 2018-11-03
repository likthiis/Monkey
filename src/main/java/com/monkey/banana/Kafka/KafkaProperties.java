package com.monkey.banana.Kafka;

public interface KafkaProperties {
    final static String zkConnect = "192.168.96.131:2181";
    final static String groupId = "bananice";
    final static String topic = "starrevue";

    final static String kafkaServerURL = "192.168.96.131";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 20000;
    final static int reconnectInterval = 10000;

    // 其他可用的topic
    final static String topic2 = "topic2";
    final static String topic3 = "topic3";
    final static String clientId = "Karen";
}
