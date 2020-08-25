package com.example.demo.controller;

import com.example.demo.components.MyTopicConsumer;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

//    @Autowired
//    private MyTopicConsumer myTopicConsumer;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public Message sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        kafkaTemplate.send("kafka-demo", message);
        return message;
    }
}
