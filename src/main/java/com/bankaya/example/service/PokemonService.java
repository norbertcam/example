package com.bankaya.example.service;

import com.bankaya.example.dto.PokemonData;
import com.bankaya.wsdl.pokemon.GetAbilitiesRequest;
import com.bankaya.wsdl.pokemon.GetAbilitiesResponse;
import com.bankaya.wsdl.pokemon.GetBaseExperienceRequest;
import com.bankaya.wsdl.pokemon.GetBaseExperienceResponse;
import com.bankaya.wsdl.pokemon.GetHeldItemsRequest;
import com.bankaya.wsdl.pokemon.GetHeldItemsResponse;
import com.bankaya.wsdl.pokemon.GetIdRequest;
import com.bankaya.wsdl.pokemon.GetIdResponse;
import com.bankaya.wsdl.pokemon.GetLocalAreaEncountersRequest;
import com.bankaya.wsdl.pokemon.GetLocalAreaEncountersResponse;
import com.bankaya.wsdl.pokemon.GetNameRequest;
import com.bankaya.wsdl.pokemon.GetNameResponse;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

public interface PokemonService {

  GetNameResponse getPokemonName(GetNameRequest request, PokemonData pokemonData);

  GetIdResponse getPokemonId(GetIdRequest request, PokemonData pokemonData);

  GetLocalAreaEncountersResponse getLocalAreaEncounters(
      GetLocalAreaEncountersRequest request, PokemonData pokemonData);

  GetHeldItemsResponse getHeldItemsRequest(GetHeldItemsRequest request, PokemonData pokemonData);

  GetAbilitiesResponse getAbilities(
      @RequestPayload GetAbilitiesRequest request,  PokemonData pokemonData);

  GetBaseExperienceResponse getBaseExperience(
      @RequestPayload GetBaseExperienceRequest request,  PokemonData pokemonData);

}
