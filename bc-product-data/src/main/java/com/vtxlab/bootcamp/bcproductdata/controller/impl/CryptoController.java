package com.vtxlab.bootcamp.bcproductdata.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.controller.CryptoOperation;
import com.vtxlab.bootcamp.bcproductdata.infra.Scheme;
import com.vtxlab.bootcamp.bcproductdata.mapper.UriCompBuilder;
import com.vtxlab.bootcamp.bcproductdata.model.CoinId;
import com.vtxlab.bootcamp.bcproductdata.service.CoinIdService;
import com.vtxlab.bootcamp.bcproductdata.service.CryptoService;

@RestController
@RequestMapping(value = "/crypto/coingecko/api/v1")
public class CryptoController implements CryptoOperation {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CryptoService cryptoService;

  public void storeCoinsToDB() throws JsonProcessingException {
    cryptoService.storeCoinsToDB();
  }

}
