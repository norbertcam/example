package com.bankaya.example.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {

  @JsonProperty("name")
  private String name;

  @JsonProperty("url")
  private String url;

}
