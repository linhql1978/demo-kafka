package com.example.demo.components;

import com.example.demo.config.KafkaConstants;
import com.example.demo.model.Message;
import lombok.Getter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class MyTopicConsumer {

    private final List<Message> messages = new ArrayList<>();

    @KafkaListener(
            topics = "kafka-demo"

    )
    public void listen(Message message) {
        synchronized (message) {
            messages.add(message);
            System.out.println(message.toString());
        }
    }
}
