package com.vtxlab.bootcamp.bcproductdata.mapper;

import org.springframework.web.util.UriComponentsBuilder;
import com.vtxlab.bootcamp.bcproductdata.infra.Currency;
import com.vtxlab.bootcamp.bcproductdata.infra.Scheme;
import lombok.Getter;

@Getter
public class UriCompBuilder {
  public static String url(Scheme scheme, String domain, String basepath,
      String endpoint) {

    return UriComponentsBuilder.newInstance() //
        .scheme(scheme.name().toLowerCase()) //
        .host(domain) //
        .path(basepath) //
        .path(endpoint) //
        .toUriString();
  }

    public static String url(Scheme scheme, String host, int port, String basepath,
        String endpoint) {
  
      return UriComponentsBuilder.newInstance() //
          .scheme(scheme.name().toLowerCase()) //
          .host(host) //
          .port(port)
          .path(basepath) //
          .path(endpoint) //
          .toUriString();
    }

  public static String url(Scheme scheme, String domain, String basepath,
      String endpoint, String key) {


    return UriComponentsBuilder.newInstance() //
        .scheme(scheme.name().toLowerCase()) //
        .host(domain) //
        .path(basepath) //
        .path(endpoint) //
        .queryParam("x_cg_demo_api_key", key) //
        .toUriString();
  }

  public static String url(Scheme scheme, String domain, String basepath,
      String endpoint, Currency currency, String key) {
    return UriComponentsBuilder.newInstance() //
        .scheme(scheme.name().toLowerCase()) //
        .host(domain) //
        .path(basepath) //
        .path(endpoint) //
        .queryParam("vs_currency", currency.name().toLowerCase()) //
        .queryParam("x_cg_demo_api_key", key) //
        .toUriString();
  }

  public static String url(Scheme scheme, String domain, String basepath,
      String endpoint, Currency currency, String key, String... ids) {

    StringBuilder idsString = new StringBuilder("");

    for (int i = 0; i < ids.length; i++) {
      idsString.append(ids[i]);
      if (i < ids.length - 1) {
        idsString.append(",");
      }
    }


    String urlString = UriComponentsBuilder.newInstance() //
        .scheme(scheme.name().toLowerCase()) //
        .host(domain) //
        .path(basepath) //
        .path(endpoint) //
        .queryParam("vs_currency", currency.name().toLowerCase()) //
        .queryParam("ids", idsString.toString()) //
        .queryParam("x_cg_demo_api_key", key) //
        .toUriString();

    return urlString;
  }


}
