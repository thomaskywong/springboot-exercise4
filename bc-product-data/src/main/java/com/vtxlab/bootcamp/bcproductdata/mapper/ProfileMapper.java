package com.vtxlab.bootcamp.bcproductdata.mapper;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import com.vtxlab.bootcamp.bcproductdata.dto.jph.Profile2;
import com.vtxlab.bootcamp.bcproductdata.entity.ProfileEntity;

@Component
public class ProfileMapper {

  public ProfileEntity mapProfileEntity(Profile2 profile, LocalDateTime quoteDate, String quoteStockCode) {
    return ProfileEntity.builder() //
            .quoteDate(quoteDate)// to be prepared before calling method
            .quoteStockCode(quoteStockCode)// to be prepared before calling method
            .country(profile.getCountry())//
            .currency(profile.getCurrency())//
            .estimatedCurrency(profile.getEstimateCurrency())//
            .exchange(profile.getExchange())//
            .finnhubindustry(profile.getFinnhubIndustry())//
            .ipo(profile.getIpo())//
            .logo(profile.getLogo())//
            .marketCapitalization(profile.getMarketCapitalization())//
            .name(profile.getName())//
            .phone(profile.getPhone())//
            .shareOutstanding(profile.getShareOutstanding())//
            .ticker(profile.getTicker())//
            .weburl(profile.getWeburl())//
            .build();
  }

  
}
