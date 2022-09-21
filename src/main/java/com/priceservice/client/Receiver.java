package com.priceservice.client;

import com.priceservice.config.PriceApplicationConfig;

import com.priceservice.model.DTO.MessageDTO;
import com.priceservice.model.PlanetarySystem;
import com.priceservice.service.PriceCalculator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
class Receiver {

    @RabbitListener(queues = PriceApplicationConfig.PRICE_SERVICE_QUEUE_NAME)
    public void receiveProductAndSendPrice(String callMessageAsJson) {
        System.out.println("RECEIVED product: " + callMessageAsJson);

        MessageDTO messageDTO = PriceCalculator.parseMessageToDTO(callMessageAsJson);

        ArrayList<PlanetarySystem> products = messageDTO.getPlanetarySystems();

        products = PriceCalculator.setCalculatedPricesForProducts(products);

        messageDTO.setPlanetarySystems(products);

        String responseMessageAsString = PriceCalculator.parseMessageDTOToJson(messageDTO);

        Sender.sendCalculatedPrice(responseMessageAsString);
    }
}
