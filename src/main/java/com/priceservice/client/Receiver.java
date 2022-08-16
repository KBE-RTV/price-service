package com.priceservice.client;

import com.priceservice.config.PriceApplicationConfig;

import com.priceservice.service.PriceCalculator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class Receiver {

    @RabbitListener(queues = PriceApplicationConfig.PRICE_SERVICE_QUEUE_NAME)
    public void receiveProductAndSendPrice(String productAsJson) {
        String calculatedPriceAsJson = PriceCalculator.calculatePriceForProduct(productAsJson);

        System.out.println("RECEIVED product: " + productAsJson);

        Sender.sendCalculatedPrice(calculatedPriceAsJson);
    }

}
