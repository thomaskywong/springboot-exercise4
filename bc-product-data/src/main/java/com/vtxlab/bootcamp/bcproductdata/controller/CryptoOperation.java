package com.vtxlab.bootcamp.bcproductdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CryptoOperation {

  @GetMapping(value = "/coins/markets/save")
  @ResponseStatus(value = HttpStatus.OK)
  Boolean storeCoinsToDB() throws JsonProcessingException;

  @GetMapping(value = "/coins/markets/clear")
  @ResponseStatus(value = HttpStatus.OK)
  Boolean clearCoinsFromDB() throws JsonProcessingException;

  
  @GetMapping(value = "/coins/entites/save")
  @ResponseStatus(value = HttpStatus.OK)
  Boolean storeCoinEntitiesToDB() throws JsonProcessingException;

  @GetMapping(value = "/coins/entites/clear")
  @ResponseStatus(value = HttpStatus.OK)
  Boolean clearCoinEntitiesFromDB() throws JsonProcessingException;

}
