package com.pathfinder.subscriber.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SubscriberConfig{

    @Bean
    public Map<String, Object> kafkaConsumerMap() {
        System.out.println("initializing kafkaConsumerMap...");
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        System.out.println("initializing consumerFactory...");
        return new DefaultKafkaConsumerFactory<>(kafkaConsumerMap());
    }
    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
        System.out.println("initializing kafkaListenerContainerFactory...");
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // Set the number of concurrent consumers
        // single instance with 3 threads
        factory.setConcurrency(3); // Set the number of concurrent consumers
        // Set manual acknowledgment mode and defualt is BATCH
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        System.out.println("initializing kafkaListenerContainerFactory sucessfully...");
        return factory;
    }
}
