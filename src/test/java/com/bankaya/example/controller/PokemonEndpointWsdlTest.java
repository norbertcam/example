package com.bankaya.example.controller;

import com.bankaya.example.service.PokemonService;
import com.bankaya.wsdl.pokemon.GetNameResponse;
import com.bankaya.wsdl.pokemon.PokemonName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.payload;
import static org.springframework.ws.test.server.ResponseMatchers.validPayload;
import static org.springframework.ws.test.server.ResponseMatchers.xpath;

@WebServiceServerTest
@ComponentScan("com.bankaya.example")
@ActiveProfiles("test")
class PokemonEndpointWsdlTest {

  @Autowired
  private MockWebServiceClient client;

  @MockBean
  PokemonService pokemonService;


  @Test
  void getPokemonName() throws IOException {
    PokemonName pokemonName = new PokemonName();
    pokemonName.setName("snorlax");
    GetNameResponse getNameResponse = new GetNameResponse();
    getNameResponse.setPokemonName(pokemonName);

    when(pokemonService.getPokemonName(any(), any())).thenReturn(getNameResponse);

    StringSource request = new StringSource(
        "<pok:getNameRequest xmlns:pok='http://bankaya.com/wsdl/pokemon'>" +
            "<pok:name>pikachu</pok:name>" +
            "</pok:getNameRequest>"
    );

    StringSource expectedResponse = new StringSource(
"<ns2:getNameResponse xmlns:ns2='http://bankaya.com/wsdl/pokemon'>" +
        "<ns2:pokemonName>" +
        "<ns2:name>snorlax</ns2:name>" +
        "</ns2:pokemonName>" +
        "</ns2:getNameResponse>"
    );

    client.sendRequest(withPayload(request))
        .andExpect(noFault())
        .andExpect(validPayload(new ClassPathResource("templates/xml/pokemon.xsd")))
        .andExpect(payload(expectedResponse))
        .andExpect(xpath("/pok:getProductResponse/pok:product[1]/pok:name", createMapping())
            .evaluatesTo("snorlax"));
  }

  private static Map<String, String> createMapping() {
    Map<String, String> mapping = new HashMap<>();
    mapping.put("pok", "http://bankaya.com/wsdl/pokemon");
    return mapping;
  }
}