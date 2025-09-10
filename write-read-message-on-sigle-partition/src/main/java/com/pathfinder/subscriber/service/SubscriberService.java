package com.pathfinder.subscriber.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService{

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    // @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "subscriber-group")
    // Specify the partitions you want to listen to
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "subscriber-group", containerFactory = "kafkaListenerContainerFactory",
            topicPartitions= @TopicPartition(topic = "${spring.kafka.template.default-topic}", partitions = {"0","1"}))

    public void processSubscriberData(String data){
        System.out.println("Received subscriber data: " + data);
        System.out.println("Subscriber data processed successfully.");
    }
}
