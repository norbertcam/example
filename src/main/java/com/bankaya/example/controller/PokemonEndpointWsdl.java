package com.bankaya.example.controller;

import com.bankaya.example.dto.PokemonData;
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
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * soap pokemon webservice.
 */
@Endpoint
public class PokemonEndpointWsdl {
  private static final String NAMESPACE_URI = "http://bankaya.com/wsdl/pokemon";
  private static final Logger logger = LoggerFactory.getLogger(PokemonEndpointWsdl.class);
  @Autowired
  private PokemonService pokemonService;
  private HttpServletRequest httpServletRequest;
  @Autowired
  public void setRequest(HttpServletRequest request) {
    this.httpServletRequest = request;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
  @ResponsePayload
  public GetNameResponse getPokemonName(@RequestPayload GetNameRequest request) {
    logger.info("Get pokemon name: {}", request.getName());

    return pokemonService.getPokemonName(request, getRequestData());
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdRequest")
  @ResponsePayload
  public GetIdResponse getPokemonId(@RequestPayload GetIdRequest request) {
    logger.info("Get pokemon id for: {}", request.getName());
    return pokemonService.getPokemonId(request, getRequestData());
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocalAreaEncountersRequest")
  @ResponsePayload
  public GetLocalAreaEncountersResponse getLocalAreaEncounters(
      @RequestPayload GetLocalAreaEncountersRequest request) {
    logger.info("Get pokemon local area encounters for: {}", request.getName());
    return pokemonService.getLocalAreaEncounters(request, getRequestData());
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHeldItemsRequest")
  @ResponsePayload
  public GetHeldItemsResponse getHeldItemsRequest(@RequestPayload GetHeldItemsRequest request) {
    logger.info("Get pokemon held items for: {}", request.getName());
    return pokemonService.getHeldItemsRequest(request, getRequestData());
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
  @ResponsePayload
  public GetAbilitiesResponse getAbilities(@RequestPayload GetAbilitiesRequest request) {
    logger.info("Get pokemon abilities for: {}", request.getName());
    return pokemonService.getAbilities(request, getRequestData());
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBaseExperienceRequest")
  @ResponsePayload
  public GetBaseExperienceResponse getBaseExperience(@RequestPayload GetBaseExperienceRequest request) {
    logger.info("Get pokemon base experience for: {}", request.getName());
    return pokemonService.getBaseExperience(request, getRequestData());
  }

  private PokemonData getRequestData() {
    return PokemonData.builder()
        .originIp(this.httpServletRequest.getRemoteAddr())
        .soapMethod((String) this.httpServletRequest.getAttribute("soapMethod"))
        .build();
  }
}
