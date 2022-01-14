package de.marius.kafkaconsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("hallo")
    public String moinsens (){
        return "Moin Moin vom Consumer";
    }
}
