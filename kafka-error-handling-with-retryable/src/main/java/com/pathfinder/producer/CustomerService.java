package com.pathfinder.producer;

import com.pathfinder.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CustomerService{

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${spring.kafka.topic}")
    private String topicName;

    public void processCustomerData(Customer customer){
        System.out.println("Processing customer data: " + customer);
        final CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName,customer);

        future.whenComplete((result,exp) -> {
            if (exp != null) {
                System.out.println("Error sending message to Kafka topic: " + exp.getMessage());
            }
            else {
                System.out.println("Message sent to Kafka topic successfully: " + result.getProducerRecord().value());
                System.out.println("Topic: " + result.getProducerRecord().topic());
                System.out.println("Partition: " + result.getRecordMetadata().partition());
                System.out.println("Offset: " + result.getRecordMetadata().offset());

            }
        });

    }


}
