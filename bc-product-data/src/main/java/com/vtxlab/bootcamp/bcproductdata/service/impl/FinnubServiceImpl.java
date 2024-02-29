package com.vtxlab.bootcamp.bcproductdata.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Profile2;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Quote;
import com.vtxlab.bootcamp.bcproductdata.entity.ProfileEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.QuoteEntity;
import com.vtxlab.bootcamp.bcproductdata.entity.StockIdEntity;
import com.vtxlab.bootcamp.bcproductdata.exception.FinnhubNotAvailableException;
import com.vtxlab.bootcamp.bcproductdata.infra.Scheme;
import com.vtxlab.bootcamp.bcproductdata.infra.Syscode;
import com.vtxlab.bootcamp.bcproductdata.mapper.ProfileMapper;
import com.vtxlab.bootcamp.bcproductdata.mapper.QuoteMapper;
import com.vtxlab.bootcamp.bcproductdata.mapper.StockIdMapper;
import com.vtxlab.bootcamp.bcproductdata.mapper.UriCompBuilder;
import com.vtxlab.bootcamp.bcproductdata.model.ApiRespProfile;
import com.vtxlab.bootcamp.bcproductdata.model.ApiRespQuote;
import com.vtxlab.bootcamp.bcproductdata.model.StockId;
import com.vtxlab.bootcamp.bcproductdata.model.StockSymbol;
import com.vtxlab.bootcamp.bcproductdata.repository.StockIdRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.StockProfileRepository;
import com.vtxlab.bootcamp.bcproductdata.repository.StockQuoteRepository;
import com.vtxlab.bootcamp.bcproductdata.service.FinnhubService;

@Service
public class FinnubServiceImpl implements FinnhubService {
  @Value(value = "${api.internal.stock.host}")
  private String host;

  @Value(value = "${api.internal.stock.port}")
  private int port;

  @Value(value = "${api.internal.stock.basepath}")
  private String basepath;

  @Value(value = "${api.internal.stock.endpoints.quote}")
  private String quoteEndpoint;

  @Value(value = "${api.internal.stock.endpoints.profile2}")
  private String profileEndpoint;

  @Value(value = "${api.internal.stock.endpoints.symbols}")
  private String symbolEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockIdRepository stockIdRepository;

  @Autowired
  private StockQuoteRepository stockQuoteRepository;

  @Autowired
  private StockProfileRepository stockProfileRepository;

  @Autowired
  private StockIdMapper stockIdMapper;

  @Autowired
  private QuoteMapper quoteMapper;

  @Autowired
  private ProfileMapper profileMapper;


  @Override
  public Boolean saveProfilesToDB() throws JsonProcessingException {

    // System.out.println("Check1");
    // Get updated profile
    List<StockIdEntity> idEntities = stockIdRepository.findAll();

    List<StockId> ids = idEntities.stream()//
        .map(e -> stockIdMapper.mapSymbolId(e))//
        .collect(Collectors.toList());

    List<ProfileEntity> profileEntities = new LinkedList<>();

    for (StockId id : ids) {

      // System.out.println("stockId=" + id);

      String urlString = UriCompBuilder.urlProfile(Scheme.HTTP, host, port,
          basepath, profileEndpoint, id);

      // System.out.println(urlString);

      ApiRespProfile apiRespProfile =
          restTemplate.getForObject(urlString, ApiRespProfile.class);

      try {
        Objects.requireNonNull(apiRespProfile);
      } catch (NullPointerException ex) {
        throw new FinnhubNotAvailableException(
            Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
      }

      Profile2 profile = apiRespProfile.getData();

      // get updated quote time
      String urlString2 = UriCompBuilder.urlQuote(Scheme.HTTP, host, port,
          basepath, quoteEndpoint, id);

      ApiRespQuote apiRespQuote =
          restTemplate.getForObject(urlString2, ApiRespQuote.class);

      try {
        Objects.requireNonNull(apiRespQuote);
      } catch (NullPointerException ex) {
        throw new FinnhubNotAvailableException(
            Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
      }

      Quote quote = apiRespQuote.getData();

      long timestamp = quote.getT();
      Instant instant = Instant.ofEpochSecond(timestamp);
      LocalDateTime localDateTime =
          LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

      ProfileEntity profileEntity =
          profileMapper.mapProfileEntity(profile, localDateTime, id);

      profileEntities.add(profileEntity);

    }

    // save data to db
    // System.out.println("List=" + profileEntities);

    stockProfileRepository.saveAll(profileEntities);

    return true;
  }

  @Override
  public Boolean storeAAPLProfileToDB() throws JsonProcessingException {

    // Get profile
    StockId id = StockId.builder()//
        .stockId(StockSymbol.AAPL.name())//
        .build();

    String urlString = UriCompBuilder.urlProfile(Scheme.HTTP, host, port,
        basepath, profileEndpoint, id);

    ApiRespProfile apiRespProfile =
        restTemplate.getForObject(urlString, ApiRespProfile.class);

    try {
      Objects.requireNonNull(apiRespProfile);
    } catch (NullPointerException ex) {
      throw new FinnhubNotAvailableException(
          Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
    }

    Profile2 profile = apiRespProfile.getData();

    // get updated quote time
    String urlString2 = UriCompBuilder.urlQuote(Scheme.HTTP, host, port,
        basepath, quoteEndpoint, id);

    ApiRespQuote apiRespQuote =
        restTemplate.getForObject(urlString2, ApiRespQuote.class);

    try {
      Objects.requireNonNull(apiRespQuote);
    } catch (NullPointerException ex) {
      throw new FinnhubNotAvailableException(
          Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
    }

    Quote quote = apiRespQuote.getData();

    long timestamp = quote.getT();
    Instant instant = Instant.ofEpochSecond(timestamp);
    LocalDateTime localDateTime =
        LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

    // map to entity
    ProfileEntity profileEntity =
        profileMapper.mapProfileEntity(profile, localDateTime, id);
    
    System.out.println(profileEntity);

    stockProfileRepository.save(profileEntity);

    return true;

  }

  @Override
  public Boolean clearProfilesFromDB() {
    stockProfileRepository.deleteAll();
    return true;
  }


  @Override
  public Boolean saveQuotesToDB() throws JsonProcessingException {

    List<StockIdEntity> idEntities = stockIdRepository.findAll();

    List<StockId> ids = idEntities.stream()//
        .map(e -> stockIdMapper.mapSymbolId(e))//
        .collect(Collectors.toList());

    List<QuoteEntity> quoteEntities = new LinkedList<>();

    for (StockId id : ids) {

      String urlString = UriCompBuilder.urlQuote(Scheme.HTTP, host, port,
          basepath, quoteEndpoint, id);

      // System.out.println("url=" + urlString);

      ApiRespQuote apiResp =
          restTemplate.getForObject(urlString, ApiRespQuote.class);

      try {
        Objects.requireNonNull(apiResp);
      } catch (NullPointerException ex) {
        throw new FinnhubNotAvailableException(
            Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
      }

      Quote quote = apiResp.getData();
      QuoteEntity quoteEntity = quoteMapper.mapQuoteEntity(quote, id);

      quoteEntities.add(quoteEntity);
    }

    // System.out.println("List=" + quoteEntities);

    stockQuoteRepository.saveAll(quoteEntities);

    return true;

  }

  @Override
  public Boolean clearQuotesFromDB() {
    stockQuoteRepository.deleteAll();
    return true;
  }

  @Override
  public Boolean storeAAPLQuoteToDB() throws JsonProcessingException {

    StockId id = StockId.builder()//
        .stockId(StockSymbol.AAPL.name())//
        .build();

    String urlString = UriCompBuilder.urlQuote(Scheme.HTTP, host, port,
        basepath, quoteEndpoint, id);

    // System.out.println("url=" + urlString);

    ApiRespQuote apiResp =
        restTemplate.getForObject(urlString, ApiRespQuote.class);

    try {
      Objects.requireNonNull(apiResp);
    } catch (NullPointerException ex) {
      throw new FinnhubNotAvailableException(
          Syscode.FINNHUB_NOT_AVAILABLE_EXCEPTION);
    }

    Quote quote = apiResp.getData();
    QuoteEntity quoteEntity = quoteMapper.mapQuoteEntity(quote, id);

    stockQuoteRepository.save(quoteEntity);

    return true;

  }

}
