package com.kbertv.priceService.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kbertv.priceService.model.PlanetarySystem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@ToString
public class MessageDTO implements Serializable {

    private UUID requestID;
    private ArrayList<PlanetarySystem> planetarySystems;
    private boolean priceCalculated;
    public MessageDTO(@JsonProperty("requestID") UUID requestID,
                                    @JsonProperty("planetarySystems") ArrayList<PlanetarySystem> planetarySystems) {
        this.requestID = requestID;
        this.planetarySystems = planetarySystems;
        this.priceCalculated = isPriceCalculated();
    }

    /**
     * Helper Method to check if all prices of the request are calculated
     *
     * @return true if price is calculated, else false
     */
    public boolean isPriceCalculated() {
        boolean flag = true;
        for (PlanetarySystem planetarySystem : planetarySystems) {
            if (planetarySystem.getPrice() == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
