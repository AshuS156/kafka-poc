package com.pathfinder.consumer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class SubscribeOrderService{

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" , groupId = "order-group" )
    public void processOrderData(String orderData , @Header("kafka_receivedTopic") String topicName,
                                 @Header("kafka_receivedPartitionId") int partition,
                                 @Header("kafka_offset") long offset) {
        System.out.println("Received order data from topic '" + topicName + "': " + orderData);
        System.out.println("Partition: " + partition);
        System.out.println("Offset: " + offset);
        System.out.println("Order data processed successfully.");
    }
}
