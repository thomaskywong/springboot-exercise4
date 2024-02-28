package com.vtxlab.bootcamp.bcproductdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vtxlab.bootcamp.bcproductdata.entity.QuoteEntity;

@Repository
public interface StockQuoteRepository extends JpaRepository<QuoteEntity,Long> {
  
}
