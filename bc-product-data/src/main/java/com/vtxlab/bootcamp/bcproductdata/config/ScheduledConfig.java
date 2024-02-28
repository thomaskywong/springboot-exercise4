package com.vtxlab.bootcamp.bcproductdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.vtxlab.bootcamp.bcproductdata.service.CryptoService;

@Configuration
@EnableScheduling
public class ScheduledConfig {

  @Autowired
  private CryptoService cryptoService;

  // @Scheduled(fixedRate = 30000)
  @Scheduled(cron = "0 * * * * *") // every xx:xx:00
  void saveCryptoToDB() throws JsonProcessingException {
    cryptoService.storeCoinsToDB();
  }
}
