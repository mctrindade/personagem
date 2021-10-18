package br.com.personagem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.model.Personagem;

/**
 * @author Marcos.Diniz
 * 
 */
@Mapper
public interface PersonagemMapper {
	
	PersonagemMapper MAPPER = Mappers.getMapper(PersonagemMapper.class );
	
	/**
	 * Method responsible for mapping {@link PersonagemDto} for {@link Personagem}
	 * @param dto - dto {@link PersonagemDto}
	 * @return Personagem - entity {@link Personagem}
	 */
	Personagem personagemDtoToPersonagem(PersonagemDto dto);
}
