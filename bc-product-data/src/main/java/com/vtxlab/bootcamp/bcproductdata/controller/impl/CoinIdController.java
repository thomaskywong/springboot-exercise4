package com.vtxlab.bootcamp.bcproductdata.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.controller.CoinIdOperation;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Coin;
import com.vtxlab.bootcamp.bcproductdata.model.CoinId;
import com.vtxlab.bootcamp.bcproductdata.service.CoinIdService;
import com.vtxlab.bootcamp.bcproductdata.service.CryptoService;

@RestController
@RequestMapping(value = "/crypto/api/v1")
public class CoinIdController implements CoinIdOperation {

  @Autowired
  private CoinIdService coinIdService;

  @Override
  public List<CoinId> setCoinIds(List<CoinId> coins) throws JsonProcessingException{
    return coinIdService.setCoinId(coins);
  }

  @Override
  public List<CoinId> getCoinIds() throws JsonProcessingException{
    return coinIdService.getCoinIds();
  }

  @Override
  public List<Coin> getCoins() throws JsonProcessingException {
    return coinIdService.getCoins();
  }

  @Override
  public Boolean deleteCoinId(List<CoinId> coins) throws JsonProcessingException {
    
    return coinIdService.deleteCoinId(coins);
  }

  @Override
  public Boolean deleteAllCoinId() throws JsonProcessingException {
    
    return coinIdService.deleteAllCoinIds();

  }
 
  
}
