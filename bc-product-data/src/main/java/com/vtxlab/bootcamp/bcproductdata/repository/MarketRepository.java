package com.vtxlab.bootcamp.bcproductdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtxlab.bootcamp.bcproductdata.entity.MarketEntity;

public interface MarketRepository extends JpaRepository<MarketEntity, Long> {
  
}
