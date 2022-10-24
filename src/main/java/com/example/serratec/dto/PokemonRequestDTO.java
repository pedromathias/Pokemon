package com.example.serratec.dto;

import org.springframework.web.multipart.MultipartFile;

public class PokemonRequestDTO {

	private String nome;

	private String tipo;

	private String descricao;
	
	private String moveset;

	private MultipartFile imagemPokemon;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMoveset() {
		return moveset;
	}

	public void setMoveset(String moveset) {
		this.moveset = moveset;
	}

	public MultipartFile getImagemPokemon() {
		return imagemPokemon;
	}

	public void setImagemPokemon(MultipartFile imagemPokemon) {
		this.imagemPokemon = imagemPokemon;
	}

	
}
