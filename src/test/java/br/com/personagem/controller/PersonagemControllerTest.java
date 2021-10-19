package br.com.personagem.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.enums.PatrounsEnum;
import br.com.personagem.enums.RolesEnum;
import br.com.personagem.exception.PersonagemException;
import br.com.personagem.mapper.PersonagemMapper;
import br.com.personagem.model.Personagem;
import br.com.personagem.service.PersonagemService;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonagemControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private PersonagemService personagemService;
	
	@MockBean
	private PersonagemMapper mapper;
	
	@Test
	void postSavePersonagemSuccessTest() throws Exception {
		PersonagemDto personagemDto = getPersonagemDto();
		Personagem personagemDtoToPersonagem = mapper.personagemDtoToPersonagem(personagemDto);
		Mockito.when(personagemService.save(personagemDto)).thenReturn(personagemDtoToPersonagem);
		mockMvc.perform(post("/api/filme/v1/personagem")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(personagemDto)))
		        .andExpect(status().isCreated());
	}
	
	@Test
	void postSavePersonagemPersonagemExceptionTest() throws Exception {
		PersonagemDto personagemDto = getPersonagemDto();
		Mockito.when(personagemService.save(personagemDto)).thenThrow(PersonagemException.class);
		mockMvc.perform(post("/api/filme/v1/personagem")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(personagemDto)))
		        .andExpect(status().isNotFound());
	}
	
	@Test
	void putUpdatePersonagemSuccessTest() throws Exception {
		Long id = 1L;
		PersonagemDto personagemDto = getPersonagemDto();
		Personagem personagemDtoToPersonagem = mapper.personagemDtoToPersonagem(personagemDto);
		Mockito.when(personagemService.update(id,personagemDto)).thenReturn(personagemDtoToPersonagem);
		mockMvc.perform(put("/api/filme/v1/personagem/"+id)
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(personagemDto)))
		        .andExpect(status().isNoContent());
	}
	
	@Test
	void putSavePersonagemPersonagemExceptionTest() throws Exception {
		Long id = 1L;
		PersonagemDto personagemDto = getPersonagemDto();
		Mockito.when(personagemService.update(id,personagemDto)).thenThrow(PersonagemException.class);
		mockMvc.perform(put("/api/filme/v1/personagem/"+id)
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(personagemDto)))
		        .andExpect(status().isNotFound());
	}

	private PersonagemDto getPersonagemDto() {
		PersonagemDto personagemDto = new PersonagemDto();
		personagemDto.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
		personagemDto.setName("Marcos Célio");
		personagemDto.setPatronus(PatrounsEnum.FOX);
		personagemDto.setRole(RolesEnum.DIRECTOR);
		personagemDto.setSchool("Hogwarts School of Witchcraft and Wizardry");
		return personagemDto;
	}
}
