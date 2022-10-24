package com.example.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.serratec.dto.PokemonRequestDTO;
import com.example.serratec.dto.PokemonResponseDTO;
import com.example.serratec.exception.ResourceBadRequestException;
import com.example.serratec.exception.ResourceNotFoundException;
import com.example.serratec.model.Pokemon;
import com.example.serratec.repository.PokemonRepository;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository repositorio;

	private ModelMapper mapper = new ModelMapper();

//	public List<Pokemon> obterTodos() {
//		return repositorio.findAll();
//	}

	public List<PokemonResponseDTO> obterTodos() {
		List<Pokemon> lista = repositorio.findAll();
		var novaLista = new ArrayList<PokemonResponseDTO>();
		for (Pokemon pokemon : lista) {
			novaLista.add(mapper.map(pokemon, PokemonResponseDTO.class));
		}
		return novaLista;
	}

	public Optional<PokemonResponseDTO> obterPorId(Long id) {
		Optional<Pokemon> optPokemon = repositorio.findById(id);
		if (optPokemon.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar o pokemon com id " + id);
		}
		PokemonResponseDTO dto = mapper.map(optPokemon.get(), PokemonResponseDTO.class);
		return Optional.of(dto);
	}

	public PokemonResponseDTO cadastrar(PokemonRequestDTO pokemon) {
		validarNomePokemon(pokemon);
		validarDescricao(pokemon);
		validarTipo(pokemon);
		validarMoveset(pokemon);
		List<Pokemon> pokemonDescricao = repositorio.findByDescricao(pokemon.getDescricao());
		if (pokemonDescricao.size() > 0) {
			throw new ResourceBadRequestException("Descrição já cadastrada!");
		}
//		var pokemonModel = mapper.map(pokemon, Pokemon.class);
		var pokemonModel = new Pokemon();
		pokemonModel.setNome(pokemon.getNome());
		pokemonModel.setDescricao(pokemon.getDescricao());
		pokemonModel.setTipo(pokemon.getTipo());
		pokemonModel.setMoveset(pokemon.getMoveset());
		pokemonModel.setImagemPokemon(converterImagemBase64(pokemon.getImagemPokemon()));
		pokemonModel = repositorio.save(pokemonModel);
		var response = mapper.map(pokemonModel, PokemonResponseDTO.class);
		return response;
	}

	public PokemonResponseDTO atualizar(Long id, PokemonRequestDTO pokemon) {
		obterPorId(id);
		var pokemonModel = mapper.map(pokemon, Pokemon.class);
		pokemonModel.setId(id);
		pokemonModel = repositorio.save(pokemonModel);
		return mapper.map(pokemonModel, PokemonResponseDTO.class);
	}

	public void deletar(Long id) {
		obterPorId(id);
		repositorio.deleteById(id);
	}

	private void validarNomePokemon(PokemonRequestDTO pokemon) {
		if (pokemon.getNome() == null) {
			throw new ResourceBadRequestException("O nome deve ser informado");
		} else if (pokemon.getNome().length() > 30)
		{
			throw new ResourceBadRequestException("Tamanho máximo de 30 caracteres no nome");
		}

	}

	private void validarDescricao(PokemonRequestDTO pokemon) {
		if (pokemon.getDescricao().length() > 50)
		{
			throw new ResourceBadRequestException("Tamanho máximo de 50 caracteres na descrição");
		}

	}

	private void validarTipo(PokemonRequestDTO pokemon) {
		if (pokemon.getTipo() == null) {
			throw new ResourceBadRequestException("O tipo do Pokemon deve ser informado");
		}

	}
	
	private void validarMoveset(PokemonRequestDTO pokemon) {
		if (pokemon.getMoveset() == null) {
			throw new ResourceBadRequestException("O moveset do Pokemon deve ser informado");
		}

	}

	private String converterImagemBase64(MultipartFile imagem) {
		if(imagem == null) {
			throw new ResourceBadRequestException("A imagem é obrigatória");
		}
		try {
			byte[] imageByteArray = Base64.encodeBase64(imagem.getBytes());
			String result = new String(imageByteArray);
			return result;
		} catch (Exception e) {
			throw new ResourceBadRequestException("Erro na conversão de imagem");
		}

	}
	

}