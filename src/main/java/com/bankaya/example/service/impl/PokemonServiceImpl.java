package com.bankaya.example.service.impl;

import com.bankaya.example.dto.PokemonData;
import com.bankaya.example.dto.pokemon.Pokemon;
import com.bankaya.example.repository.PokemonDataRepository;
import com.bankaya.example.repository.entity.PokemonDataEntity;
import com.bankaya.example.service.PokemonFeignClient;
import com.bankaya.example.service.PokemonService;
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
import com.bankaya.wsdl.pokemon.PokemonAbilities;
import com.bankaya.wsdl.pokemon.PokemonBaseExperience;
import com.bankaya.wsdl.pokemon.PokemonHeldItems;
import com.bankaya.wsdl.pokemon.PokemonId;
import com.bankaya.wsdl.pokemon.PokemonLocalAreaEncounters;
import com.bankaya.wsdl.pokemon.PokemonName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service for pokemon.
 */
@Service
public class PokemonServiceImpl implements PokemonService {

  private static final Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);

  @Autowired
  private PokemonFeignClient feignClientPokemon;

  @Autowired
  private PokemonDataRepository pokemonRepository;

  @Override
  public GetNameResponse getPokemonName(GetNameRequest request, PokemonData pokemonData) {
    logger.info("Retrieving pokemon name for : {}", request.getName());
    GetNameResponse response = new GetNameResponse();

    Pokemon pokemon =  retrievePokemonFromApi(request.getName());

    PokemonName p = new PokemonName();
    p.setName(pokemon.getName());
    response.setPokemonName(p);
    savePokemonData(pokemonData);
    return response;
  }

  @Override
  public GetIdResponse getPokemonId(GetIdRequest request, PokemonData pokemonData) {
    logger.info("Retrieving id for : {}", request.getName());
    GetIdResponse response = new GetIdResponse();

    Pokemon pokemon =  retrievePokemonFromApi(request.getName());

    PokemonId p = new PokemonId();
    p.setId(pokemon.getId());
    response.setPokemonId(p);
    savePokemonData(pokemonData);
    return response;
  }

  @Override
  public GetLocalAreaEncountersResponse getLocalAreaEncounters(
      GetLocalAreaEncountersRequest request, PokemonData pokemonData) {
    logger.info("Retrieving local area encounters for : {}", request.getName());
    GetLocalAreaEncountersResponse response = new GetLocalAreaEncountersResponse();

    Pokemon pokemon =  retrievePokemonFromApi(request.getName());

    PokemonLocalAreaEncounters p = new PokemonLocalAreaEncounters();
    p.setLocationAreaEncounters(pokemon.getLocationAreaEncounters());
    response.setPokemonLocalAreaEncounters(p);
    savePokemonData(pokemonData);
    return response;
  }

  @Override
  public GetHeldItemsResponse getHeldItemsRequest(
      GetHeldItemsRequest request, PokemonData pokemonData) {
    logger.info("Retrieving held items for : {}", request.getName());
    GetHeldItemsResponse response = new GetHeldItemsResponse();

    Pokemon pokemon =  retrievePokemonFromApi(request.getName());

    List<PokemonHeldItems> items = pokemon.getHeldItems().stream().map(item -> {
      PokemonHeldItems heldItems = new PokemonHeldItems();
      heldItems.setName(item.getItem().getName());
      heldItems.setUrl(item.getItem().getUrl());
      return heldItems;
    }).toList();

    response.getPokemonHeldItems().addAll(items);
    savePokemonData(pokemonData);
    return response;
  }

  @Override
  public GetAbilitiesResponse getAbilities(
      GetAbilitiesRequest request, PokemonData pokemonData) {
    logger.info("Retrieving abilities for : {}", request.getName());
    GetAbilitiesResponse response = new GetAbilitiesResponse();

    Pokemon pokemon =  retrievePokemonFromApi(request.getName());

    List<PokemonAbilities> abilities = pokemon.getAbilities().stream().map(ability -> {
      PokemonAbilities abilityMap = new PokemonAbilities();
      abilityMap.setName(ability.getAbility().getName());
      abilityMap.setUrl(ability.getAbility().getUrl());
      return abilityMap;
    }).toList();

    response.getPokemonAbilities().addAll(abilities);
    savePokemonData(pokemonData);
    return response;
  }

  @Override
  public GetBaseExperienceResponse getBaseExperience(
      GetBaseExperienceRequest request, PokemonData pokemonData) {
    logger.info("Retrieving base experience for : {}", request.getName());
    GetBaseExperienceResponse response = new GetBaseExperienceResponse();

    Pokemon pokemon =  retrievePokemonFromApi(request.getName());

    PokemonBaseExperience p = new PokemonBaseExperience();
    p.setBaseExperience(pokemon.getBaseExperience());
    response.setPokemonBaseExperience(p);
    savePokemonData(pokemonData);
    return response;
  }

  private void savePokemonData(PokemonData pokemonData) {
    logger.info("Saving in database ip: {}", pokemonData);
    PokemonDataEntity pokemonDataEntity = pokemonRepository.save(
        PokemonDataEntity.builder()
            .originIp(pokemonData.getOriginIp())
            .method(pokemonData.getSoapMethod())
            .dateCreated(new Date())
            .build());
    logger.info("Saved in database {}", pokemonDataEntity);
  }

  private Pokemon retrievePokemonFromApi(String name) {
    Pokemon pokemon =  feignClientPokemon.getPokemonByName(name);
    logger.info("Pokemon retrieved: {}", pokemon);

    return pokemon;
  }

}
