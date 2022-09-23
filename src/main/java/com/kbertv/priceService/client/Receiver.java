package com.kbertv.priceService.client;

import com.kbertv.priceService.model.DTO.MessageDTO;
import com.kbertv.priceService.model.PlanetarySystem;
import com.kbertv.priceService.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
class Receiver {

    @Autowired
    Sender sender;

    @Autowired
    PriceService priceService;

    @RabbitListener(queues = {"${rabbitmq.priceService.queue.call}"})
    public String receiveProductAndSendPrice(String callAsJson) {
        log.info("RECEIVED product: " + callAsJson);

        MessageDTO messageDTO = priceService.parseMessageToDTO(callAsJson);

        ArrayList<PlanetarySystem> planetarySystems = messageDTO.getPlanetarySystems();

        planetarySystems = priceService.setCalculatedPricesForProducts(planetarySystems);

        messageDTO.setPlanetarySystems(planetarySystems);

        String responseMessageAsString = PriceService.parseMessageDTOToJson(messageDTO);

        return responseMessageAsString;
    }
}
