package com.priceservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.priceservice.model.CelestialBody;
import com.priceservice.model.DTO.MessageDTO;
import com.priceservice.model.PlanetarySystem;

import java.util.ArrayList;

public class PriceCalculator {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static MessageDTO parseMessageToDTO(String messageAsJson) {
        MessageDTO messageDTO;

        try {
            messageDTO = objectMapper.readValue(messageAsJson, MessageDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return messageDTO;
    }

    public static ArrayList<PlanetarySystem> setCalculatedPricesForProducts(ArrayList<PlanetarySystem> products) {

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

        try {
            messageAsJson = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return messageAsJson;
    }


}
