package com.vtxlab.bootcamp.bcproductdata.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Coin;
import com.vtxlab.bootcamp.bcproductdata.entity.CoinIdEntity;
import com.vtxlab.bootcamp.bcproductdata.exception.InvalidCoinException;
import com.vtxlab.bootcamp.bcproductdata.infra.ApiResponse;
import com.vtxlab.bootcamp.bcproductdata.infra.Scheme;
import com.vtxlab.bootcamp.bcproductdata.infra.Syscode;
import com.vtxlab.bootcamp.bcproductdata.mapper.CoinIdMapper;
import com.vtxlab.bootcamp.bcproductdata.mapper.UriCompBuilder;
import com.vtxlab.bootcamp.bcproductdata.model.CoinId;
import com.vtxlab.bootcamp.bcproductdata.repository.CoinIdRepository;
import com.vtxlab.bootcamp.bcproductdata.service.CoinIdService;
import com.vtxlab.bootcamp.bcproductdata.service.CryptoService;

@Service
public class CoinIdServiceImpl implements CoinIdService {

  @Value(value = "${api.internal.crypto.host}")
  private String host;

  @Value(value = "${api.internal.crypto.port}")
  private int port;

  @Value(value = "${api.internal.crypto.basepath}")
  private String basepath;

  @Value(value = "${api.internal.crypto.endpoints.list}")
  private String listEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CoinIdRepository coinIdRepository;

  @Autowired
  private CryptoService cryptoService;

  @Autowired
  private CoinIdMapper coinIdMapper;

  @Override
  public List<CoinId> setCoinId(List<CoinId> ids)
      throws JsonProcessingException {

    List<Coin> coins = cryptoService.getCoins();

    Set<CoinId> coinIds = coinIdRepository.findAll() //
        .stream() //
        .map(e -> coinIdMapper.mapCoinId(e)) //
        .collect(Collectors.toSet());

    for (CoinId id : ids) {
      if (!(Coin.isValidCoin(coins, id.getCoinId()))) {
        throw new InvalidCoinException(Syscode.INVALID_COIN);
      }
    }

    coinIds.addAll(ids);

    List<CoinIdEntity> entities = coinIds.stream() //
        .map(e -> coinIdMapper.mapCoinIdEntity(e)) //
        .collect(Collectors.toList());

    coinIdRepository.deleteAll();
    coinIdRepository.saveAll(entities);

    // List<CoinIdEntity> updatedEntities = coinIdRepository.findAll();

    return ids;

  }

  @Override
  public List<CoinId> getCoinIds() throws JsonProcessingException {

    List<CoinIdEntity> updatedEntities = coinIdRepository.findAll();

    return updatedEntities.stream() //
        .map(e -> coinIdMapper.mapCoinId(e)) //
        .collect(Collectors.toList());

  }

  @Override
  public Boolean deleteCoinId(List<CoinId> ids) throws JsonProcessingException {

    // System.out.println("Delete Coin.");
    Set<CoinId> coinIds = coinIdRepository.findAll() //
        .stream() //
        .map(e -> coinIdMapper.mapCoinId(e)) //
        .collect(Collectors.toSet());

    for (CoinId id : ids) {
      if (!(coinIds.contains(id))) {
        return false;
      }
    }

    coinIds.removeAll(ids);

    List<CoinIdEntity> entities = coinIds.stream() //
        .map(e -> coinIdMapper.mapCoinIdEntity(e)) //
        .collect(Collectors.toList());

    coinIdRepository.deleteAll();
    coinIdRepository.saveAll(entities);

    return true;

  }

  @Override
  public Boolean deleteAllCoinIds() throws JsonProcessingException {

    coinIdRepository.deleteAll();

    return true;
  }

  @Override
  public List<Coin> getCoins() throws JsonProcessingException {
    // String urlString = "http://localhost:8090/crypto/coingecko/api/v1/coins/list";

    String urlString = UriCompBuilder.url(Scheme.HTTP, host, port, basepath, listEndpoint);
    ApiResponse<List<Coin>> apiResp = restTemplate.getForObject(urlString, ApiResponse.class);

    return apiResp.getData();

  }

}
