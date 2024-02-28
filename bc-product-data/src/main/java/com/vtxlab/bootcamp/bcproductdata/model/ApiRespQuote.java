package com.vtxlab.bootcamp.bcproductdata.model;

import com.vtxlab.bootcamp.bcproductdata.dto.jph.Quote;
import lombok.Getter;


@Getter
public class ApiRespQuote {

  private String code;
  private String message;
  private Quote data;

}
