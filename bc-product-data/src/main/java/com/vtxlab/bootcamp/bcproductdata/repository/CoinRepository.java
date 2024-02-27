package com.vtxlab.bootcamp.bcproductdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtxlab.bootcamp.bcproductdata.entity.CoinIdEntity;

public interface CoinRepository extends JpaRepository<CoinIdEntity, Long> {
  
}
