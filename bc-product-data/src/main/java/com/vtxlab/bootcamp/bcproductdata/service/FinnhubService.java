package com.vtxlab.bootcamp.bcproductdata.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface FinnhubService {

    Boolean saveQuotesToDB() throws JsonProcessingException;

    Boolean clearQuotesFromDB() throws JsonProcessingException;

}
