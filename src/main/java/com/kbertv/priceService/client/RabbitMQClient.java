package com.kbertv.priceService.client;

import com.kbertv.priceService.model.dto.MessageDTO;
import com.kbertv.priceService.model.PlanetarySystem;
import com.kbertv.priceService.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
class RabbitMQClient {

    @Autowired
    PriceService priceService;

    @RabbitListener(queues = {"${rabbitmq.priceService.queue.call}"})
    public String receiveProductsAndSendPrice(String messageAsJson) {
        log.info("RECEIVED product: " + messageAsJson);

        MessageDTO messageDTO = priceService.parseJsonToMessageDTO(messageAsJson);

        ArrayList<PlanetarySystem> planetarySystems = messageDTO.getPlanetarySystems();
        planetarySystems = priceService.setCalculatedPricesForPlanetarySystems(planetarySystems);
        messageDTO.setPlanetarySystems(planetarySystems);

        String responseMessageAsString = PriceService.parseMessageDTOToJson(messageDTO);

        return responseMessageAsString;
    }
}
