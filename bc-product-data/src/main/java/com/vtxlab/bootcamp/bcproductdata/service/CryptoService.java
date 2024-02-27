package com.vtxlab.bootcamp.bcproductdata.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CryptoService {

  void storeCoinsToDB() throws JsonProcessingException;
  
}
