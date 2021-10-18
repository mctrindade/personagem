package br.com.personagem.mapper.impl;

import org.springframework.stereotype.Component;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.mapper.PersonagemMapper;
import br.com.personagem.model.Personagem;

/**
 * @author Marcos.Diniz
 *	Class responsible for implementing {@link PersonagemMapper}
 */
@Component
public class PersonagemMapperImpl implements PersonagemMapper {

	@Override
	public Personagem personagemDtoToPersonagem(PersonagemDto dto) {
		Personagem personagem = new Personagem();
		personagem.setHouse(dto.getHouse());
		personagem.setName(dto.getName());
		personagem.setSchool(dto.getSchool());
		personagem.setPatronus(dto.getPatronus());
		personagem.setRole(dto.getRole());
		return personagem;
	}

}
