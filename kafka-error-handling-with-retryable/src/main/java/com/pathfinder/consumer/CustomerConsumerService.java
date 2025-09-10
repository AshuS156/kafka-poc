package com.pathfinder.consumer;

import com.pathfinder.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerConsumerService{

    @Value("${spring.kafka.topic}")
    private String topicName;

    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    @RetryableTopic(attempts = "4", // Number of retry attempts
            backoff = @Backoff(delay = 2000 , multiplier = 2 , maxDelay = 20000) // Delay between retries in milliseconds
    )
    public void consumeCustomerData(Customer customerData ,@Header(KafkaHeaders.RECEIVED_TOPIC) String topicName,
                                    @Header(KafkaHeaders.PARTITION) int partition,
                                    @Header(KafkaHeaders.OFFSET) long offset) {

        System.out.println("Received customer data: " + customerData);
        System.out.println("Topic: " + topicName);
        System.out.println("Partition: " + partition);
        System.out.println("Offset: " + offset);

        List<String> ipAddressList = Arrays.asList("12.22.343.24", "12.22.343.25", "12.22.343.26");
        if( customerData.getIpAddress() != null &&
            ipAddressList.contains(customerData.getIpAddress())) {
            System.out.println("Invalid IP address: " + customerData.getIpAddress());
          throw new RuntimeException("Invalid IP address: " + customerData.getIpAddress());
        }


    }

    @DltHandler
    public void consumeCustomerDataFallback(Customer customerData, @Header("kafka_receivedTopic") String topicName,
                                    @Header("kafka_receivedPartitionId") int partition,
                                    @Header("kafka_offset") long offset) {
        System.out.println("Fallback method called for customer data: " + customerData);
        System.out.println("Topic: " + topicName);
        System.out.println("Partition: " + partition);
        System.out.println("Offset: " + offset);
    }
}
