package com.priceservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.priceservice.model.PlanetarySystem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class MessageDTO implements Serializable {

    private UUID requestID;
    private ArrayList<PlanetarySystem> planetarySystems;

    public MessageDTO(@JsonProperty("requestID") UUID requestID,
                      @JsonProperty("planetarySystems") ArrayList<PlanetarySystem> planetarySystems) {
        this.requestID = requestID;
        this.planetarySystems = planetarySystems;
    }
}
