package com.vtxlab.bootcamp.bcproductdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vtxlab.bootcamp.bcproductdata.entity.CoinEntity;

@Repository
public interface CoinRepository extends JpaRepository<CoinEntity, Long>{

  // @Query(value = "DELETE FROM coinEntity")
  // void deleteAllEntities();
}
