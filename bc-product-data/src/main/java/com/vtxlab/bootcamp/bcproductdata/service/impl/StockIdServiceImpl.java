package com.vtxlab.bootcamp.bcproductdata.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Symbol;
import com.vtxlab.bootcamp.bcproductdata.entity.StockIdEntity;
import com.vtxlab.bootcamp.bcproductdata.exception.InvalidStockSymbolException;
import com.vtxlab.bootcamp.bcproductdata.infra.Syscode;
import com.vtxlab.bootcamp.bcproductdata.mapper.StockIdMapper;
import com.vtxlab.bootcamp.bcproductdata.model.StockId;
import com.vtxlab.bootcamp.bcproductdata.repository.StockIdRepository;
import com.vtxlab.bootcamp.bcproductdata.service.FinnhubService;
import com.vtxlab.bootcamp.bcproductdata.service.StockIdService;

@Service
public class StockIdServiceImpl implements StockIdService {

  @Autowired
  private FinnhubService finnhubService;

  @Autowired
  private StockIdRepository stockIdRepository;

  @Autowired
  private StockIdMapper stockIdMapper;

  @Override
  public List<StockId> setStockId(List<StockId> ids)
      throws JsonProcessingException {
    List<Symbol> symbols = finnhubService.getSymbols();

    Set<StockId> stockIds = stockIdRepository.findAll() //
        .stream() //
        .map(e -> stockIdMapper.mapSymbolId(e)) //
        .collect(Collectors.toSet());

    for (StockId id : ids) {
      if (!(Symbol.isValidSymbol(symbols, id.getStockId()))) {
        throw new InvalidStockSymbolException(Syscode.INVALID_STOCK_SYMBOL);
      }
    }

    stockIds.addAll(ids);

    List<StockIdEntity> entities = stockIds.stream() //
        .map(e -> stockIdMapper.mapSymbolIdEntity(e)) //
        .collect(Collectors.toList());

    stockIdRepository.deleteAll();
    stockIdRepository.saveAll(entities);

    return ids;
  }

  @Override
  public List<StockId> getStockIds() throws JsonProcessingException {
    return stockIdRepository.findAll() //
        .stream() //
        .map(e -> stockIdMapper.mapSymbolId(e)) //
        .collect(Collectors.toList());
  }

    @Override
  public Boolean deleteStockId(List<StockId> ids) throws JsonProcessingException {

    // System.out.println("Delete Coin.");
    Set<StockId> stockIds = stockIdRepository.findAll() //
        .stream() //
        .map(e -> stockIdMapper.mapSymbolId(e)) //
        .collect(Collectors.toSet());

    for (StockId id : ids) {
      if (!(stockIds.contains(id))){
        return false;
      }
    }

    stockIds.removeAll(ids);

    List<StockIdEntity> entities = stockIds.stream() //
        .map(e -> stockIdMapper.mapSymbolIdEntity(e)) //
        .collect(Collectors.toList());

    stockIdRepository.deleteAll();
    stockIdRepository.saveAll(entities);

    return true;

  }


  @Override
  public Boolean deleteAllStockIds() throws JsonProcessingException {
    
    stockIdRepository.deleteAll();

    return true;
  }

}
