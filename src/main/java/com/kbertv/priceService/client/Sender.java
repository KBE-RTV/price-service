package com.kbertv.priceService.client;

import com.kbertv.priceService.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Sender {

    @Value("${rabbitmq.priceService.key.response}")
    public String priceServiceCallRoutingKey;

    public static RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        Sender.rabbitTemplate = rabbitTemplate;
    }


    public void sendCalculatedPrice(String responseAsJson)
    {
        rabbitTemplate.convertAndSend(RabbitMQConfig.exchange.getName(), priceServiceCallRoutingKey, responseAsJson);

        log.info("SENT calculated price: " + responseAsJson);

    }
}
