package com.priceservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PriceCalculator {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static String calculatePriceForProduct(String productAsJson){
        double calculatedPrice = 0;
        int id = 0;
        JsonNode productNode = null;

        try {
            JsonNode node = objectMapper.readValue(productAsJson, JsonNode.class);
            if(node.has("id"))
                id = node.get("id").asInt();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ID: " + id);

        try {
            productNode = objectMapper.readTree(productAsJson).get("celestialBodies");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (productNode.isArray()) {
            for (JsonNode componentNode : productNode) {
                calculatedPrice += componentNode.get("price").asDouble();
                System.out.println(componentNode + "\n");
            }
        }
        return "{ " + "\"id\" : " + id +", \"price\" : " + calculatedPrice + " }";
    }

}
