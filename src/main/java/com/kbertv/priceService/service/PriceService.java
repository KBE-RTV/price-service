package com.kbertv.priceService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbertv.priceService.model.CelestialBody;
import com.kbertv.priceService.model.DTO.MessageDTO;
import com.kbertv.priceService.model.PlanetarySystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PriceService {

    static ObjectMapper objectMapper;

    public MessageDTO parseMessageToDTO(String messageAsJson) {
        MessageDTO messageDTO;

        objectMapper = new ObjectMapper();

        try {
            messageDTO = objectMapper.readValue(messageAsJson, MessageDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return messageDTO;
    }

    public ArrayList<PlanetarySystem> setCalculatedPricesForProducts(ArrayList<PlanetarySystem> products) {

        for (PlanetarySystem product : products) {

            ArrayList<CelestialBody> components = product.getCelestialBodies();

            float calculatedPrice = 0;

            for (CelestialBody component : components) {
                calculatedPrice += component.getPrice();
            }

            product.setPrice(calculatedPrice);
        }

        return products;
    }

    public static String parseMessageDTOToJson(MessageDTO message) {
        String messageAsJson;

        objectMapper = new ObjectMapper();

        try {
            messageAsJson = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return messageAsJson;
    }


}
