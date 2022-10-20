package com.training.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    @RabbitListener(queues = {Config.QUEUE, "queue_a", "queue_b"})
    public void listener(Event message){
        System.out.println(message);
    }
}
