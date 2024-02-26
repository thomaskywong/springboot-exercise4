package com.vtxlab.bootcamp.bcproductdata.mapper;

import org.springframework.stereotype.Component;
import com.vtxlab.bootcamp.bcproductdata.entity.StockIdEntity;
import com.vtxlab.bootcamp.bcproductdata.model.StockId;

@Component
public class StockIdMapper {

  
  public StockId mapCoinId(StockIdEntity entity) {
    return StockId.builder() //
                 .stockId(entity.getStockId()) //
                 .build();
  }

  public StockIdEntity mapCoinId(StockId id) {
    return StockIdEntity.builder() //
                 .stockId(id.getStockId()) //
                 .build();
  }
  
  
}
