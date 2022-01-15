package de.marius.kafkaconsumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnConsumer {

    @Value("${topic.name.consumer}")
    private String topicName;
    public static List<ConsumerRecord<String, String>> messages = new ArrayList<>();

//    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id", topicPartitions = {
//            @TopicPartition(topic = "${topic.name.consumer}",
//                    partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0"))
//    })
    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload) {
        messages.add(payload);
        log.info("Konsumiertes Topic: {}", topicName);
        log.info("Key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());

    }
}
