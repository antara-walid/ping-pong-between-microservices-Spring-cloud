package com.example.ms3.service;

import com.example.ms3.domain.Message;
import com.example.ms3.enumerations.Microservices;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Service
public class ServiceImpl implements MyService {


    RestTemplate restTemplate;

    public ServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Message initialProcess() {

        // 1. Create the first message
        Message initialMessage = new Message(UUID.randomUUID().toString(), Microservices.MS3,"MS3",0);
        // 2. Send the message
        return sendMessage(initialMessage);
    }

    @Override
    public Message processMessage(Message message) {

        // 1. Concat the microService name to existing body
        String newBody = message.getBody().concat("-> MS3 ");
        // 2. Create the message to be sent with the new body
        Message messageToBeSent = new Message(UUID.randomUUID().toString(),Microservices.MS3, newBody, message.getCounter()+1);
        // 3. Send Message
        return sendMessage(messageToBeSent);
    }

    @Override
    public Message sendMessage(Message message) {

        // This methode has being made separately in order to have the ability to change the protocol used
        // in communication between microservices or the destination of requests
        String destinationUrl = "lb://"+"DISPATCHER"+"/process";
        return restTemplate.postForObject(destinationUrl,message,Message.class);
    }
}
