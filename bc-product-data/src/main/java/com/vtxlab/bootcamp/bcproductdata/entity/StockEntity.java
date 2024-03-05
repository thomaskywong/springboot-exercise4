package com.vtxlab.bootcamp.bcproductdata.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @Builder
@ToString
@Entity
@Table(name = "tproduct_stocks")
public class StockEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @Column(name = "coin_id")
  // private Long coinId;

  @Column(name = "name")
  private String name;

  @Column(name = "curr_price")
  private Double currPrice;

  @Column(name = "price_chg_pct")
  private Double priceChangePercent;

  @Column(name = "market_cap")
  private Double marketCap;

  @Column(name = "logo")
  private String logo;

  @OneToOne
  @JoinColumn(name = "stock_id")
  @JsonBackReference
  private StockIdEntity stockIdEntity;
}
