package com.vtxlab.bootcamp.bcproductdata.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.controller.QuoteOperation;
import com.vtxlab.bootcamp.bcproductdata.service.FinnhubService;


@RestController
@RequestMapping(value = "/stock/database/api/v1")
public class QuoteController implements QuoteOperation{

    @Autowired
    private FinnhubService finnhubService;

    @Override
    public Boolean saveQuoteToDB() throws JsonProcessingException {
      return finnhubService.saveQuotesToDB();
    }

    @Override
    public Boolean clearQuotesFromDB() throws JsonProcessingException {
      return finnhubService.clearQuotesFromDB();
    }
  
}
