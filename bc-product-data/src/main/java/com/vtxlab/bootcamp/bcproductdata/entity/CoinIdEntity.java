package com.vtxlab.bootcamp.bcproductdata.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @Builder
// @ToString
@Entity
@Table(name = "tproduct_coins_list")
public class CoinIdEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "coin_code")
  private String coinId;

  // @OneToOne(mappedBy = "coinIdEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  // @JsonManagedReference
  // private CoinEntity CoinEntity;

}
