package com.vtxlab.bootcamp.bcproductdata.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Symbol;

public interface FinnhubService {
    List<Symbol> getSymbols() throws JsonProcessingException;
}
