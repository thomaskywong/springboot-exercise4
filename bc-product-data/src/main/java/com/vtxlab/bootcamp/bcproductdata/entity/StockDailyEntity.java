package com.vtxlab.bootcamp.bcproductdata.entity;

import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@ToString
@Entity
@Table(name = "tproduct_stocks_daily")
public class StockDailyEntity implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "trade_date")
  private LocalDate tradeDate;

  @Column(name = "day_high")
  private Double dayHigh;

  @Column(name = "day_low")
  private Double dayLow;

  @Column(name = "day_open")
  private Double dayOpen;

  @Column(name = "day_close")
  private Double dayClose;
  
  @ManyToOne
  @JoinColumn(name = "stock_id")
  @JsonBackReference
  private StockIdEntity stockIdEntity2;
  
}

