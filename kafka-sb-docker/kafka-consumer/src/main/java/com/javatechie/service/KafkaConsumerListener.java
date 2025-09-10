package com.javatechie.service;

import com.javatechie.dto.Customer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener{

    @KafkaListener(topics = "javatechie-topic", groupId = "pathfinder-group")
     public void listen1(String message) {
         System.out.println("Received message listen1: " + message);
     }

    @KafkaListener(topics = "javatechie-topic", groupId = "pathfinder-group")
    public void listen2(String message) {
        System.out.println("Received message listen2: " + message);
    }

    @KafkaListener(topics = "javatechie-topic", groupId = "pathfinder-group")
    public void listen3(String message) {
        System.out.println("Received message listen3: " + message);
    }

    @KafkaListener(topics = "javatechie-topic", groupId = "pathfinder-group")
    public void listen4(String message) {
        System.out.println("Received message listen4: " + message);
    }


        @KafkaListener(topics = "customerTopic", groupId = "customerTopic-group")
        public void listen(Customer customer) {
            System.out.println("Received customer on : " + customer.toString());
        }
}
