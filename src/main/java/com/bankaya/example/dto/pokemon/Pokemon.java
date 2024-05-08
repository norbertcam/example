package com.bankaya.example.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class Pokemon {

  @JsonProperty("abilities")
  private List<Abilities> abilities;

  @JsonProperty("base_experience")
  private Integer baseExperience;

  @JsonProperty("held_items")
  private List<HeldItems> heldItems;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("url")
  private String url;

  @JsonProperty("location_area_encounters")
  private String locationAreaEncounters;

}
