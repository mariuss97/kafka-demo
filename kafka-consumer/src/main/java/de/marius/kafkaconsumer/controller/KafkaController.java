package de.marius.kafkaconsumer.controller;

import de.marius.kafkaconsumer.OwnConsumer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final OwnConsumer ownConsumer;

    //private final KafkaConsumer kafkaConsumer;

//    @GetMapping(value = "/receive")
//    public void send(@RequestParam("message") String message){
//
//        consumer.consume();
//    }
@GetMapping(value = "/receiveAll")
public List<ConsumerRecord<String, String>> receiveAll(){
    System.out.println("receiveAll called");

//    kafkaConsumer.subscribe(Collections.singletonList("mariusTestProducerTopic"));
//    kafkaConsumer.poll(Duration.ofMillis(100));
//    kafkaConsumer.seekToBeginning(Collections.emptySet());
//    kafkaConsumer.poll(Duration.ofMillis(500000));//no loop to simplify
//    OwnConsumer.kafkaConsumer.poll(Duration.ofMillis(0));
//    OwnConsumer.kafkaConsumer.seekToBeginning(Collections.emptySet());

//    for (ConsumerRecord record : OwnConsumer.kafkaConsumer.poll(Duration.ofMillis(0)).records("mariusTestProducerTopic")) {
//        // process a record
//    }

    return OwnConsumer.messages;
}

}
