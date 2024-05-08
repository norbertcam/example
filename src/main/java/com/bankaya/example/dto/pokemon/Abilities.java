package com.bankaya.example.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Abilities {

    @JsonProperty("ability")
    private Ability ability;

    @JsonProperty("is_hidden")
    private boolean isHidden;

    @JsonProperty("slot")
    private Integer slot;

}
