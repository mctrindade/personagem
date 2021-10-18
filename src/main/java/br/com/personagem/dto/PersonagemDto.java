package br.com.personagem.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.personagem.enums.PatrounsEnum;
import br.com.personagem.enums.RolesEnum;
import br.com.personagem.validator.PatrounsOfEnum;
import br.com.personagem.validator.RolesOfEnum;
import lombok.Data;

@Data
public class PersonagemDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "The full name is required.")
	@Size(min = 4, max = 500, message = "The length of full name must be between 4 and 500 characters.")
	private String name;
	
	@RolesOfEnum(anyOf = {RolesEnum.DIRECTOR, RolesEnum.STUDENT, RolesEnum.TEACHER})
	private RolesEnum role;
	
	@NotEmpty(message = "the school is blank!!")
	private String school;
	
	@NotEmpty(message = "the house is blank!!")
	private String house;
	
	@PatrounsOfEnum(anyOf = {PatrounsEnum.FOX, PatrounsEnum.OTTER, PatrounsEnum.STAG})
	private PatrounsEnum patronus;
	
	

}
