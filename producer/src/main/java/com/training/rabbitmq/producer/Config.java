package com.training.rabbitmq.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public static final String QUEUE = "user_event_queue";
    public static final String TOPIC_EXCHANGE = "user_event_exchange";

    public static final String ROUTING_KEY = "user_event_routing_key";
    public static final String ROUTING_KEY_A = "queue.a";
    public static final String ROUTING_KEY_B = "queue.b";
    public static final String ROUTING_KEY_C = "queue.*";

    public static final String FANOUT_EXCHANGE = "exchange.fanout";
    public static final String QUEUE_A = "queue.a";
    public static final String QUEUE_B = "queue.b";

    public static final String QUEUE_AUTO = "queue.auto";


    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public Queue queueA(){
        return new Queue(QUEUE_A);
    }

    @Bean
    public Queue queueB(){
        return new Queue(QUEUE_B);
    }

    @Bean
    public Queue queueAuto(){
        return new Queue(QUEUE_AUTO);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public FanoutExchange exchangeFan() {return new FanoutExchange(FANOUT_EXCHANGE);}

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding bindingA(Queue queueA, TopicExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_KEY_A);
    }

    @Bean
    public Binding bindingB(Queue queueB, TopicExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_KEY_B);
    }

    @Bean
    public Binding bindingAuto(Queue queueAuto, TopicExchange exchange){
        return BindingBuilder.bind(queueAuto).to(exchange).with(ROUTING_KEY_C);
    }

    @Bean
    public Binding bindingD(Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindingE(Queue queueA, FanoutExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange);
    }

    @Bean
    public Binding bindingF(Queue queueB, FanoutExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
