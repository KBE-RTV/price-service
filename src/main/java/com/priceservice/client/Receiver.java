package com.priceservice.client;

import com.priceservice.config.PriceApplicationConfig;

import com.priceservice.service.PriceCalculator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
class Receiver {

    @RabbitListener(queues = PriceApplicationConfig.PRICE_SERVICE_QUEUE_NAME)
    public void receiveProductAndSendPrice(String productAsJson) {
        double calculatedPrice = PriceCalculator.calculatePriceForProduct(productAsJson);

        System.out.println("Sent calculated price: " + calculatedPrice);

        Sender.sendCalculatedPrice(calculatedPrice);
    }

}
