package com.kbertv.priceService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbertv.priceService.model.CelestialBody;
import com.kbertv.priceService.model.dto.MessageDTO;
import com.kbertv.priceService.model.PlanetarySystem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * class for calculating prices and parsing json
 */
@Service
public class PriceService {

    static ObjectMapper objectMapper;

    /**
     * calculates the prices for a list of planetary systems contained in a message dto
     * @param messageDTO
     * @return
     */
    public MessageDTO calculatePricesForMessageDTO(MessageDTO messageDTO)
    {
        ArrayList<PlanetarySystem> planetarySystems = messageDTO.getPlanetarySystems();

        planetarySystems = setCalculatedPricesForPlanetarySystems(planetarySystems);
        messageDTO.setPlanetarySystems(planetarySystems);

        return messageDTO;
    }

    /**
     * calculates the prices for a list of planetary systems
     * @param planetarySystems
     * @return list of planetary systems with calculated prices
     */
    private ArrayList<PlanetarySystem> setCalculatedPricesForPlanetarySystems(ArrayList<PlanetarySystem> planetarySystems) {

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

    /**
     * parses a json message containing a list of planetary systems to a dto
     * @param messageAsJson
     * @return planetarySystemsMessageDTO
     */
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

    /**
     * parses a message dto containing planetary systems to json
     * @param messageDTO
     * @return message as json
     */
    public static String parseMessageDTOToJson(MessageDTO messageDTO) {
        String messageAsJson;

        objectMapper = new ObjectMapper();

        try {
            messageAsJson = objectMapper.writeValueAsString(messageDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return messageAsJson;
    }


}
