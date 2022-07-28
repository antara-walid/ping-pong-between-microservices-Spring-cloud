package com.example.ms3.domain;

import com.example.ms3.enumerations.Microservices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private Microservices source;
    private String body;
    private int counter;
}
