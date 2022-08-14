package com.priceservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class PriceApplicationConfig {
    public final String TOPIC_EXCHANGE_NAME = "kbe_topic_exchange";
    public static final String PRICE_SERVICE_QUEUE_NAME = "price_call_queue";
    public final String PRICE_SERVICE_CALL_ROUTING_KEY = "priceService.call";
    public static final String PRICE_SERVICE_RESPONSE_ROUTING_KEY = "priceService.response";

    public static TopicExchange exchange;

    @Autowired
    public void setExchange(@Lazy TopicExchange exchange) {
        PriceApplicationConfig.exchange = exchange;
    }

    @Bean
    Queue queue() {
        return new Queue(PRICE_SERVICE_QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(PRICE_SERVICE_CALL_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
