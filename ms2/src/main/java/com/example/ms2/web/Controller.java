package com.example.ms2.web;

import com.example.ms2.domain.Message;
import com.example.ms2.domain.Microservices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@Slf4j
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/process")
    void initialProcess()
    {
        Message initialMessage = new Message(UUID.randomUUID().toString(), Microservices.MS1,"MS2",0);

        String destinationUrl = "lb://"+"DISPATCHER"+"/process";
        restTemplate.postForObject(destinationUrl,initialMessage,Message.class);
    }
    @PostMapping("/process")
    void processMessage(@RequestBody Message message)
    {
            // 1. ADD MicroService name to body
            String newBody = message.getBody().concat("-> MS2 ");

            Message messageToBeSent = new Message(UUID.randomUUID().toString(),Microservices.MS2, newBody, message.getCounter()+1);
            restTemplate.postForObject("lb://"+"DISPATCHER"+"/process",messageToBeSent,Message.class);

    }
}
