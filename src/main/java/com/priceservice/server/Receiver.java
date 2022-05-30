package com.priceservice.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.priceservice.config.CurrencyApplicationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.priceservice.service.PriceCalculator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class Receiver {

    @RabbitListener(queues = CurrencyApplicationConfig.queueName)
    public double receiveProductAndSendPrice(String productAsJson) {
        double calculatedPrice = PriceCalculator.calculatePriceForProduct(productAsJson);

        return calculatedPrice;
    }

}
