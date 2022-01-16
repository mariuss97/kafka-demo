package de.marius.kafkaconsumer;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

@Slf4j
@Component
public class OwnConsumerPlainKafka {

    @Autowired
    private Environment env;

    @EventListener(ApplicationStartedEvent.class)
    public void main() throws InterruptedException {

        log.info("start main of OwnConsumerPlainKafka");

        String topic = env.getProperty("topic.name.consumer");

        log.info("topicNameX: "+topic);

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
        while(consumer.assignment().isEmpty()) {
            log.info("polling before assignment...");
            consumer.poll(Duration.ofMillis(0));
        }
        log.info("assigned!");
        Thread.sleep(2000);
        log.info("end of sleep");
        consumer.seekToBeginning(Collections.emptySet());
        log.info("set to beginning");
        while(true){
        for (ConsumerRecord record : consumer.poll(Duration.ofMillis(100)).records(topic)) {
        log.info("Key: " + record.key() + ", Value:" + record.value());
        log.info("Partition:" + record.partition() + ",Offset:" + record.offset());
    }}


//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//            log.info("polling...");
//            for (ConsumerRecord<String, String> record : records) {
//                log.info("Key: " + record.key() + ", Value:" + record.value());
//                log.info("Partition:" + record.partition() + ",Offset:" + record.offset());
//
//            }
//        }
    }
}
