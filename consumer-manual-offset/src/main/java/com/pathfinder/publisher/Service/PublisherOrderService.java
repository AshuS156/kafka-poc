package com.pathfinder.publisher.Service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service("publisherService")
public class PublisherOrderService{

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    public void publishOrder(String data){
        System.out.println("Publishing  data in publishOrder method : " + data);
        final ProducerRecord<String, String> producerRecordObject = buildProducerRecord(data);
        final CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecordObject);
        future.whenComplete((result,ex) -> {
            if (ex != null) {
                System.err.println("Error publishing data: " + ex.getMessage());
            }
            else {
                System.out.println("Data published successfully: " + result.getProducerRecord().value());
                System.out.println("Partition: " + result.getRecordMetadata().partition());
                System.out.println("Offset: " + result.getRecordMetadata().offset());
            }
        });

    }

    private ProducerRecord<String, String> buildProducerRecord(String data){
        System.out.println("execution of buildProducerRecord started......" );
         return new ProducerRecord<String, String> (topicName, null, null, data);

        /*final List<RecordHeader> recordHeaders = List.of(new RecordHeader(topicName,"scanner".getBytes()));
        return new ProducerRecord<>(topicName,null,null,data,recordHeaders);*/
    }
}
