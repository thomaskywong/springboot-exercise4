package com.vtxlab.bootcamp.bcproductdata.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CryptoService {

  Boolean storeCoinsToDB() throws JsonProcessingException;

  Boolean clearCoinsFromDB() throws JsonProcessingException;

  Boolean storeBitcoinsToDB() throws JsonProcessingException;

  Boolean storeCoinEntitiesToDB() throws JsonProcessingException;

  Boolean clearCoinEntitiesFromDB() throws JsonProcessingException;

  
}
