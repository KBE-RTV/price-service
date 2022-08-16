package com.priceservice.client;

import com.priceservice.config.PriceApplicationConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    public static RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        Sender.rabbitTemplate = rabbitTemplate;
    }


    public static void sendCalculatedPrice(String calculatedPriceAsJson)
    {
        rabbitTemplate.convertAndSend(PriceApplicationConfig.exchange.getName(), PriceApplicationConfig.PRICE_SERVICE_RESPONSE_ROUTING_KEY, calculatedPriceAsJson);

        System.out.println("SENT calculated price: " + calculatedPriceAsJson + "\n");

    }
}
