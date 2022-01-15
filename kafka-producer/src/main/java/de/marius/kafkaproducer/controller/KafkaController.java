package de.marius.kafkaproducer.controller;

import de.marius.kafkaproducer.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final Producer producer;

    @PostMapping(value = "/publish")
    public void send(@RequestParam("message") String message){
        producer.send(message);
    }

}
