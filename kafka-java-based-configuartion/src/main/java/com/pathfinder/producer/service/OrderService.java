package com.pathfinder.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderService{

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void publishOrderData(String orderData) {
        System.out.println("Publishing order data: " + orderData);
        final CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName,orderData);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Error publishing order data: " + ex.getMessage());
            } else {
                System.out.println("Order data published successfully: " + result.getProducerRecord().value());
            }
        });
    }
}
