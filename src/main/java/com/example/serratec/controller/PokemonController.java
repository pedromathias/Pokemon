package com.example.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.serratec.dto.PokemonRequestDTO;
import com.example.serratec.dto.PokemonResponseDTO;
import com.example.serratec.service.PokemonService;

@RestController
@RequestMapping("/pokemons")

public class PokemonController {

	@Autowired
	private PokemonService servico;
	
	@GetMapping
	public ResponseEntity<List<PokemonResponseDTO>> obterTodos() {
		List<PokemonResponseDTO> lista = servico.obterTodos();
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PokemonResponseDTO> obterPorId(@PathVariable Long id) {
		Optional<PokemonResponseDTO> optPokemon = servico.obterPorId(id);
		if(optPokemon.isPresent()){
			return ResponseEntity.ok(optPokemon.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<PokemonResponseDTO> cadastrar(@ModelAttribute PokemonRequestDTO pokemon) {
		PokemonResponseDTO pokemonDTO = servico.cadastrar(pokemon);
		return new ResponseEntity<>(pokemonDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PokemonResponseDTO> atualizar(@PathVariable Long id, @RequestBody PokemonRequestDTO pokemon) {
		return ResponseEntity.ok(servico.atualizar(id, pokemon));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
