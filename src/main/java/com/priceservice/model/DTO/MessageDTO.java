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

    private UUID callId;
    private ArrayList<PlanetarySystem> products;

    public MessageDTO(@JsonProperty("callId") UUID callId,
                      @JsonProperty("products") ArrayList<PlanetarySystem> products) {
        this.callId = callId;
        this.products = products;
    }
}
