package com.bankaya.example.service;

import com.bankaya.example.dto.pokemon.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "jplaceholder", url = "https://pokeapi.co/api/v2")
public interface PokemonFeignClient {

  @GetMapping(value = "/pokemon/{pokemonName}", produces = { MediaType.APPLICATION_JSON_VALUE })
  Pokemon getPokemonByName(@PathVariable("pokemonName") String pokemonName);

}
