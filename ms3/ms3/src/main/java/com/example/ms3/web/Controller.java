package com.example.ms3.web;

import com.example.ms3.domain.Message;
import com.example.ms3.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class Controller {

    private final MyService myService;

    public Controller(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/process")
    Message initialProcess()
    {
        return myService.initialProcess();
    }

    @PostMapping("/process")
    Message processMessage(@RequestBody Message message)
    {
           return myService.processMessage(message);
    }
}
