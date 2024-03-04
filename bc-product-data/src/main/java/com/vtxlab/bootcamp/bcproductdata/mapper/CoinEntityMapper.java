package com.vtxlab.bootcamp.bcproductdata.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Market;
import com.vtxlab.bootcamp.bcproductdata.entity.CoinEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.CoinIdEntity;
import com.vtxlab.bootcamp.bcproductdata.model.CoinId;

@Component
public class CoinEntityMapper {

  @Autowired
  private CoinIdMapper coinIdMapper;

  public CoinEntity mapCoinEntity(Market market, CoinId id) {

    CoinIdEntity coinIdEntity = coinIdMapper.mapCoinIdEntity(id);

    CoinEntity coinEntity = CoinEntity.builder() //
                             .name(id.getCoinId()) //
                             .currPrice(market.getCurrentPrice())//
                             .priceChangePercent(market.getPriceChangePercent24h())//
                             .marketCap((double)(long)market.getMarketCap())//
                             .logo(market.getImage())//
                             .coinIdEntity(coinIdEntity)//
                             .build();
    
    coinIdEntity.setCoinEntity(coinEntity);
    
    return coinEntity;

  }
  
}
