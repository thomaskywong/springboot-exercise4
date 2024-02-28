package com.vtxlab.bootcamp.bcproductdata.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface QuoteOperation {

  @GetMapping(value = "/quote/save")
  @ResponseStatus(value = HttpStatus.OK)
  Boolean saveQuoteToDB() throws JsonProcessingException;

  @GetMapping(value = "/quote/clear")
  @ResponseStatus(value = HttpStatus.OK)
  Boolean clearQuotesFromDB() throws JsonProcessingException;

  
}
