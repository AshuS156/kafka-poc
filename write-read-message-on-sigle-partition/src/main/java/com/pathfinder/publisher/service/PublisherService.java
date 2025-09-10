package com.pathfinder.publisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PublisherService{

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @Autowired
    KafkaTemplate <String, Object> kafkaTemplate;
    public void publishDataOnSinglePartition(String data) {
        System.out.println("Publishing  data: " + data);
        final CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName,1,null,data);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Error publishing data: " + ex.getMessage());
            } else {
                System.out.println("Data published successfully: " + result.getProducerRecord().value());
                System.out.println("Partition: " + result.getRecordMetadata().partition());
                System.out.println("Offset: " + result.getRecordMetadata().offset());
            }
        });

    }
}
