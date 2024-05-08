package com.bankaya.example.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Pokemon request data.
 */
@Builder
@Data
public class PokemonData {

  private String originIp;

  private String soapMethod;

}
