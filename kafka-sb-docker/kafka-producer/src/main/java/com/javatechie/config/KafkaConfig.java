package com.javatechie.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig{

    @Value("${spring.kafka.topic}")
    String topicName;

    @Bean
    public NewTopic pathFinderTopic() {
        return new NewTopic(topicName, 3, (short) 1);
    }

    @Bean
    public NewTopic pinacTopic() {
        return TopicBuilder
                .name("pinac")
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic customerTopic() {
        return new NewTopic("customerTopic", 4, (short) 1);
    }
}
