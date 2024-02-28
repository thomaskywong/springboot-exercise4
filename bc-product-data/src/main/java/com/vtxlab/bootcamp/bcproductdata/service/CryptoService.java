package com.vtxlab.bootcamp.bcproductdata.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CryptoService {

  Boolean storeCoinsToDB() throws JsonProcessingException;

  Boolean clearCoinsFromDB() throws JsonProcessingException;
  
}
