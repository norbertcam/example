package com.bankaya.example.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeldItems {

  @JsonProperty("item")
  private Item item;

}
