package br.com.personagem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.model.Personagem;

@Mapper
public interface PersonagemMapper {
	
	PersonagemMapper MAPPER = Mappers.getMapper( PersonagemMapper.class );
	
	Personagem personagemDtoToPersonagem(PersonagemDto dto);
}
