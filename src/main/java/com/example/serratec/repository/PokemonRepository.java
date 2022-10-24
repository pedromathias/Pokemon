package com.example.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.serratec.model.Pokemon;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{
	List<Pokemon> findByDescricao(String descricao);
}
