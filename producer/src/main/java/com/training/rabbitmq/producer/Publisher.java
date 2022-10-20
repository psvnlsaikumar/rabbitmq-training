package com.training.rabbitmq.producer;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class Publisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Event message){
        message.setEventId(UUID.randomUUID().toString());
        message.setEventDate(new Date());
        message.setEventType(message.getEventType());
        template.convertAndSend(Config.TOPIC_EXCHANGE, Config.ROUTING_KEY, message);


        return "send message";
    }
}
