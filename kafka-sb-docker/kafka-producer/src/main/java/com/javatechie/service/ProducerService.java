package com.javatechie.service;

import com.javatechie.dto.Customer;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService{

    private KafkaTemplate<String, Object> kafkaTemplate;

    private final NewTopic pathFinderTopic;

    private final NewTopic customerTopic;


    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate, NewTopic pathFinderTopic , NewTopic customerTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.pathFinderTopic = pathFinderTopic;
        this.customerTopic= customerTopic;
    }



    public void sendMessage(String message) {
        final CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(pathFinderTopic.name(),message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Error sending message: " + ex.getMessage());
            } else {
                System.out.println("Message sent successfully: " + result.getProducerRecord().value());
                System.out.println("Message sent to partition: " + result.getRecordMetadata().partition() +
                                           ", offset: " + result.getRecordMetadata().offset() + ", Topic Name : " + result.getRecordMetadata().topic());
            }
        });
    }

    public void publishCustomer(Customer customer){
        try {
            final CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(customerTopic.name(),customer);
            future.whenComplete((result,ex) -> {
                if (ex != null) {
                    System.out.println("Error sending customer message: " + ex.getMessage());
                }
                else {
                    System.out.println("Customer message sent successfully: " + result.getProducerRecord().value());
                    System.out.println("Customer message sent to partition: " + result.getRecordMetadata().partition() +
                                               ", offset: " + result.getRecordMetadata().offset() + ", Topic Name : " + result.getRecordMetadata().topic());
                }
            });

        } catch (Exception e) {
            System.out.println("Error occured while publishing customer message: " + e.getMessage());
        }
    }
}
