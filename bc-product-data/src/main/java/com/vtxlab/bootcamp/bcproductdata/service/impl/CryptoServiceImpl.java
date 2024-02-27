package com.vtxlab.bootcamp.bcproductdata.service.impl;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Market;
import com.vtxlab.bootcamp.bcproductdata.exception.CoingeckoNotAvailableException;
import com.vtxlab.bootcamp.bcproductdata.infra.Currency;
import com.vtxlab.bootcamp.bcproductdata.infra.Scheme;
import com.vtxlab.bootcamp.bcproductdata.infra.Syscode;
import com.vtxlab.bootcamp.bcproductdata.mapper.UriCompBuilder;
import com.vtxlab.bootcamp.bcproductdata.model.ApiRespMarkets;
import com.vtxlab.bootcamp.bcproductdata.model.CoinId;
import com.vtxlab.bootcamp.bcproductdata.service.CoinIdService;
import com.vtxlab.bootcamp.bcproductdata.service.CryptoService;

@Service
public class CryptoServiceImpl implements CryptoService {

  @Value(value = "${api.internal.crypto.host}")
  private String host;

  @Value(value = "${api.internal.crypto.port}")
  private int port;

  @Value(value = "${api.internal.crypto.basepath}")
  private String basepath;

  @Value(value = "${api.internal.crypto.endpoints.list}")
  private String listEndpoint;

  @Value(value = "${api.internal.crypto.endpoints.markets}")
  private String marketsEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CoinIdService coinIdService;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void storeCoinsToDB() throws JsonProcessingException {

    List<CoinId> coinIds = coinIdService.getCoinIds();

    if(coinIds.size() == 0) {
      throw new CoingeckoNotAvailableException(Syscode.COINGECKO_NOT_AVAILABLE_EXCEPTION);
    }

    String ids = coinIds.stream().map(e->e.getCoinId()).collect(Collectors.joining(","));

    String urlString = UriCompBuilder.urlCoinsMarket(Scheme.HTTP, host, port, basepath, marketsEndpoint , Currency.USD, ids);

    // System.out.println(urlString);

    String jsonString = restTemplate.getForObject(urlString, String.class);

    ApiRespMarkets apiResp = objectMapper.readValue(jsonString, ApiRespMarkets.class );

    List<Market> markets = apiResp.getData();

    // System.out.println(markets);
    

  }

  

}
