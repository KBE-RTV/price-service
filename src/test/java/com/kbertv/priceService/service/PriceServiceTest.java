package com.kbertv.priceService.service;

import com.kbertv.priceService.model.CelestialBody;
import com.kbertv.priceService.model.PlanetarySystem;
import com.kbertv.priceService.model.dto.MessageDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PriceServiceTest {

    PriceService priceService;

    @BeforeEach
    void setUp() {
        priceService = new PriceService();
    }

    @Test
    void calculatePricesForMessageDTOTest() {

        CelestialBody celestialBody1 = new CelestialBody(UUID.randomUUID(), "name", 1, 10.0f, "celestialBody", 0, 0, 0f, 0, 0, 0, 0, 0f);
        CelestialBody celestialBody2 = new CelestialBody(UUID.randomUUID(), "name", 1, 20.0f, "celestialBody", 0, 0, 0f, 0, 0, 0, 0, 0f);

        PlanetarySystem planetarySystem = new PlanetarySystem(UUID.randomUUID(), "name", "owner", new ArrayList<>(Arrays.asList(celestialBody1, celestialBody2)), 0.0f);

        MessageDTO messageDTO = new MessageDTO(UUID.randomUUID(), new ArrayList<PlanetarySystem>(Arrays.asList(planetarySystem)));
        messageDTO = priceService.calculatePricesForMessageDTO(messageDTO);

        float calculatedPrice = messageDTO.getPlanetarySystems().get(0).getPrice();

        assertEquals(30.0f, calculatedPrice);

    }

}