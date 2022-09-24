package com.kbertv.priceService.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name}")
    public String topicExchangeName;
    @Value("${rabbitmq.priceService.queue.call}")
    public String priceServiceCallQueueName;
    @Value("${rabbitmq.priceService.queue.response}")
    public String priceServiceResponseQueueName;
    @Value("${rabbitmq.priceService.key.call}")
    public String priceServiceCallRoutingKey;
    @Value("${rabbitmq.priceService.key.response}")
    public String priceServiceResponseRoutingKey;

    public static TopicExchange exchange;

    @Autowired
    public void setExchange(@Lazy TopicExchange exchange) {
        RabbitMQConfig.exchange = exchange;
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Queue queue() {
        return new Queue(priceServiceCallQueueName, false);
    }

    @Bean
    Queue responseQueue() {
        return new Queue(priceServiceResponseQueueName, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange).with(priceServiceCallRoutingKey);
    }

    @Bean
    Binding responseBinding() {
        return BindingBuilder.bind(responseQueue()).to(exchange()).with(priceServiceResponseRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
