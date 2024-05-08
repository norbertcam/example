package com.bankaya.example.repository;

import com.bankaya.example.repository.entity.PokemonDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for save Pokemon data.
 */
@Repository
public interface PokemonDataRepository extends JpaRepository<PokemonDataEntity, Long>{

}
