package com.vtxlab.bootcamp.bcproductdata.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Market;
import com.vtxlab.bootcamp.bcproductdata.infra.Currency;

public interface CryptoService {

  Boolean storeCoinsToDB() throws JsonProcessingException;

  Boolean clearCoinsFromDB() throws JsonProcessingException;

  Boolean storeBitcoinsToDB() throws JsonProcessingException;

  List<Market> getMarkets(Currency currency) throws JsonProcessingException;
  
}
