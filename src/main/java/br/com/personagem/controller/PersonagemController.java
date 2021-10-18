package br.com.personagem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.exception.PersonagemException;
import br.com.personagem.model.Personagem;
import br.com.personagem.service.PersonagemService;

@RestController
@Validated
@RequestMapping(path = "/api/personagens/v1")
public class PersonagemController {
	
	private final PersonagemService personagemService;
	
	@Autowired
	public PersonagemController(PersonagemService personagemService) {
		this.personagemService = personagemService;
	}
		
	@PostMapping
	public ResponseEntity<Personagem> savePersonagem(@Valid @RequestBody PersonagemDto personagemDto) {
		try {
			Personagem personagem = personagemService.save(personagemDto);
			
			return new ResponseEntity<>(personagem, HttpStatus.CREATED);
		
		} catch (PersonagemException e) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}

	@PutMapping(value = "/personagem/{id}")
	public ResponseEntity<Personagem> updatePersonagem(@PathVariable Long id, @Valid @RequestBody PersonagemDto personagemDto) {
		try {
			Personagem personagem = personagemService.update(id,personagemDto);
			
			return new ResponseEntity<>(personagem, HttpStatus.NO_CONTENT);
		} catch (PersonagemException e) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Personagem>> getPersonagens() {
		List<Personagem> listaPersonagens = personagemService.listPersonagens();
		return new ResponseEntity<>(listaPersonagens, HttpStatus.OK);
	}
	
	@GetMapping(value = "/personagem/{id}")
	public ResponseEntity<Personagem> getPersonagem(@PathVariable Long id) {
		Personagem personagem = personagemService.findPersonagemById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("Personagem com identificador %s não existe na base", id)));
		return new ResponseEntity<>(personagem, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/personagem/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePersonagem(@PathVariable Long id) {
		try {
			personagemService.delete(id);
		} catch (PersonagemException e) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	
	
	
}
