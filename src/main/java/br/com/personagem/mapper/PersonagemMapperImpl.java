package br.com.personagem.mapper;

import org.springframework.stereotype.Component;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.model.Personagem;

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
