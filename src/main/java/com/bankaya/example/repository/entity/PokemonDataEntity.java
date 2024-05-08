package com.bankaya.example.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Pokemon data entity.
 */
@Entity
@Table(name = "pokemon_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonDataEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @Column(name = "origin_ip")
  private String originIp;

  @Column(name = "date_created")
  private Date dateCreated;

  @Column(name = "method")
  private String method;

}
