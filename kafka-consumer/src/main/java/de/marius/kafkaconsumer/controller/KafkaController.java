package de.marius.kafkaconsumer.controller;

import de.marius.kafkaconsumer.OwnConsumer;
import de.marius.kafkaconsumer.services.KafkaService;
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
    private final KafkaService kafkaService;
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
    return kafkaService.pollFromBeginning();
}

}
