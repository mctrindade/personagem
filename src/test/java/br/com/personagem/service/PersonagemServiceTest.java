package br.com.personagem.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.enums.PatrounsEnum;
import br.com.personagem.enums.RolesEnum;
import br.com.personagem.mapper.PersonagemMapper;
import br.com.personagem.model.Personagem;
import br.com.personagem.repository.PersonagemRepository;
import br.com.personagem.service.integracao.HouseIntegracaoService;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonagemServiceTest {
	
	@Mock
	private PersonagemMapper mapper;
	
	@Mock
	private PersonagemRepository personagemRepository;
	
	@Mock
	private HouseIntegracaoService houseIntegracaoService;
	
	@InjectMocks
	private PersonagemService personagemService;
	
//	@Test
	void psaveSuccessTest() throws Exception {
		PersonagemDto personagemDto = getPersonagemDto();
		//Personagem personagemDtoToPersonagem = mapper.personagemDtoToPersonagem(personagemDto);
		when(houseIntegracaoService.isValidHouseByIdHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde")).thenReturn(true);
		when(personagemRepository.save(Mockito.any())).thenReturn(new Personagem());
		Personagem personagem = personagemService.save(personagemDto);
		
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
