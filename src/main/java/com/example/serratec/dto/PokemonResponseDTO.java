package com.example.serratec.dto;

public class PokemonResponseDTO {
	
	private Long id;

	private String nome;

	private String tipo;

	private String descricao;

	private String imagemPokemon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getImagemPokemon() {
		return imagemPokemon;
	}

	public void setImagemPokemon(String imagemPokemon) {
		this.imagemPokemon = imagemPokemon;
	}
	
	
}
