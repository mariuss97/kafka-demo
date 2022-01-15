package de.marius.kafkaconsumer.controller;

import de.marius.kafkaconsumer.Consumer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final Consumer consumer;

//    @GetMapping(value = "/receive")
//    public void send(@RequestParam("message") String message){
//
//        consumer.consume();
//    }
@GetMapping(value = "/receiveAll")
public List<ConsumerRecord<String, String>> receiveAll(){

    return Consumer.messages;
}

}
