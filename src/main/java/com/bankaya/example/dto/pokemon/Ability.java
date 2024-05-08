package com.bankaya.example.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ability {
  @JsonProperty("name")
  private String name;

  @JsonProperty("url")
  private String url;
}
