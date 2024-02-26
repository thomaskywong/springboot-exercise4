package com.vtxlab.bootcamp.bcproductdata.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Coin;
import com.vtxlab.bootcamp.bcproductdata.infra.Scheme;
import com.vtxlab.bootcamp.bcproductdata.mapper.UriCompBuilder;
import com.vtxlab.bootcamp.bcproductdata.service.CryptoService;

@Service
public class CryptoServiceImpl implements CryptoService {

  @Value(value = "${api.internal.crypto.host}")
  private String host;

  @Value(value = "${api.internal.crypto.port}")
  private int port;

  @Value(value = "${api.internal.crypto.basepath}")
  private String basepath;

  @Value(value = "${api.internal.crypto.endpoints.list}")
  private String listEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<Coin> getCoins() throws JsonProcessingException {

    String urlString =
        UriCompBuilder.url(Scheme.HTTP, host, port, basepath, listEndpoint);
    
    Coin[] coins = restTemplate.getForObject(urlString, Coin[].class);

    return Arrays.stream(coins) //
        .collect(Collectors.toList());

  }

}
