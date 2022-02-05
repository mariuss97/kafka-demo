package de.marius.kafkaconsumer.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@Service
@Slf4j
public class KafkaService {

    @Autowired
    private Environment env;

    public List<ConsumerRecord<String, String>> pollFromBeginning() {
        log.info("start main of OwnConsumerPlainKafka");

        String topic = env.getProperty("topic.name.consumer");

        //Creating consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.consumer.bootstrap-servers"));
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, env.getProperty("spring.kafka.consumer.key-deserializer"));
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, env.getProperty("spring.kafka.consumer.value-deserializer"));
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.kafka.consumer.group-id"));
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, env.getProperty("spring.kafka.consumer.auto-offset-reset"));
        //creating consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //Subscribing
        consumer.subscribe(Arrays.asList(topic));
        //polling
        log.info("now poll first time...");
        //consumer.poll(Duration.ofMillis(0));
        while (consumer.assignment().isEmpty()) {
            log.info("polling before assignment...");
            consumer.poll(Duration.ofMillis(0));
        }
        log.info("assigned!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end of sleep");
        //seek to Beginning for all currently assigned partitions
        consumer.seekToBeginning(Collections.emptySet());
        log.info("set to beginning");

        List<ConsumerRecord<String, String>> messages = new ArrayList<>();
        for (ConsumerRecord record : consumer.poll(Duration.ofMillis(100)).records(topic)) {
            messages.add(record);
            log.info("Key: " + record.key() + ", Value:" + record.value());
            log.info("Partition:" + record.partition() + ",Offset:" + record.offset());
        }
        consumer.close();
        return messages;
    }
}
