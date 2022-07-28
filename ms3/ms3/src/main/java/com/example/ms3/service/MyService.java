package com.example.ms3.service;

import com.example.ms3.domain.Message;

public interface MyService {
    Message initialProcess();
    Message processMessage(Message message);
    Message sendMessage(Message message);

}
