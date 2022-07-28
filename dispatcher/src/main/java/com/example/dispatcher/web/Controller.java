package com.example.dispatcher.web;


import com.example.dispatcher.domain.Message;
import com.example.dispatcher.domain.Microservices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@Slf4j
public class Controller {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/process" )
    void processMessage(@RequestBody Message message)
    {
        log.info("entered dispatcher");
        if(message.getCounter() < 5)
        {
            int randomNumber = (int) (Math.random() *3);
            Microservices randomDestination = Microservices.values()[randomNumber];
            log.info("*******counter : {} | message with id : {} and body < {} > | source : {} | destination : {}",
                    message.getCounter(),message.getId(),message.getBody(),message.getSource().toString(),randomDestination.toString());
            restTemplate.postForObject("lb://"+randomDestination.toString()+"/process",message,Message.class);
        }
        else {
            log.info("finish *******************");
        }

    }
}
