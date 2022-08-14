package com.priceservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PriceCalculator {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static double calculatePriceForProduct(String productAsJson){
        double calculatedPrice = 0;
        JsonNode productNode = null;

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
        return calculatedPrice;
    }

}
