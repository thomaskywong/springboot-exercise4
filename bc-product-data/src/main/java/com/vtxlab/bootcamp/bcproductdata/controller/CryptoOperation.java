package com.vtxlab.bootcamp.bcproductdata.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.model.CoinId;

public interface CryptoOperation {

  @GetMapping(value = "/coins/markets/save")
  @ResponseStatus(value = HttpStatus.OK)
  void storeCoinsToDB() throws JsonProcessingException;

}
