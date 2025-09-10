package com.pathfinder.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig{

    @Value("${spring.kafka.topic}")
    private String topicName;

    @Bean
    public NewTopic customerTopic() {
        return new NewTopic(topicName, 3, (short) 1);
    }
}
