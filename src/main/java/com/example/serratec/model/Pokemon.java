package com.example.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pokemon")
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@Column(name = "tipo", nullable = false)
	private String tipo;

	@Column(name = "descricao", nullable =  true, length = 100)
	private String descricao;
	
	@Column(name= "moveset", nullable = false)
	private String moveset;

	@Column(name = "imagem_produto", nullable = false, columnDefinition = "TEXT")
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

	public String getMoveset() {
		return moveset;
	}

	public void setMoveset(String moveset) {
		this.moveset = moveset;
	}
	
	

	
}