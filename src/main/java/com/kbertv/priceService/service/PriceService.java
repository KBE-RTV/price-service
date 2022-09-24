package com.kbertv.priceService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbertv.priceService.model.CelestialBody;
import com.kbertv.priceService.model.dto.MessageDTO;
import com.kbertv.priceService.model.PlanetarySystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PriceService {

    static ObjectMapper objectMapper;

    public ArrayList<PlanetarySystem> setCalculatedPricesForPlanetarySystems(ArrayList<PlanetarySystem> planetarySystems) {

        for (PlanetarySystem planetarySystem : planetarySystems) {

            ArrayList<CelestialBody> celestialBodies = planetarySystem.getCelestialBodies();

            float calculatedPrice = 0;

            for (CelestialBody celestialBody : celestialBodies) {
                calculatedPrice += celestialBody.getPrice();
            }

            planetarySystem.setPrice(calculatedPrice);
        }

        return planetarySystems;
    }

    public MessageDTO parseJsonToMessageDTO(String messageAsJson) {
        MessageDTO messageDTO;

        objectMapper = new ObjectMapper();

        try {
            messageDTO = objectMapper.readValue(messageAsJson, MessageDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return messageDTO;
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
