package de.marius.kafkaproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    //first String: Topic, second String: message
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message){
        log.info("Payload zum Senden: {}", message);
        kafkaTemplate.send(topicName, message);
    }
}
