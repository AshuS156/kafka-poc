package com.pathfinder.subscriber.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService implements AcknowledgingMessageListener<String, String> {

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "order-group")
    public void onMessage(ConsumerRecord<String, String> data,Acknowledgment acknowledgment) {
        System.out.println("Received subscriber data: " + data);
        acknowledgment.acknowledge(); // Manually acknowledge the message
        System.out.println("Subscriber data processed successfully.");
    }
}
