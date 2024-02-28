package com.vtxlab.bootcamp.bcproductdata.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.controller.CryptoOperation;
import com.vtxlab.bootcamp.bcproductdata.service.CryptoService;

@RestController
@RequestMapping(value = "/crypto/coingecko/api/v1")
public class CryptoController implements CryptoOperation {

  @Autowired
  private CryptoService cryptoService;

  public void storeCoinsToDB() throws JsonProcessingException {
    cryptoService.storeCoinsToDB();
  }

}
