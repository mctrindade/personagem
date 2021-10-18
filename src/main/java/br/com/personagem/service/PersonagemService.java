package br.com.personagem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.personagem.dto.PersonagemDto;
import br.com.personagem.exception.HouseInexistenteException;
import br.com.personagem.exception.PersonagemException;
import br.com.personagem.exception.PersonagemInexistenteException;
import br.com.personagem.mapper.PersonagemMapper;
import br.com.personagem.model.Personagem;
import br.com.personagem.repository.PersonagemRepository;
import br.com.personagem.service.integracao.HouseIntegracaoService;

/**
 * @author Marcos.Diniz
 *	
 */
@Service
public class PersonagemService {
	
	/**
	 * 
	 */
	private final PersonagemRepository personagemRepository;
	
	/**
	 * 
	 */
	private final HouseIntegracaoService houseIntegracaoService;
	
	/**
	 * Constructor class {@link PersonagemService}
	 * @param personaRepository - repository responsible by provide {@link Personagem}
	 */
	@Autowired
	public PersonagemService(PersonagemRepository personaRepository, HouseIntegracaoService houseIntegracaoService) {
		this.personagemRepository = personaRepository;
		this.houseIntegracaoService = houseIntegracaoService;
	}
	
	
	/**
	 * method responsible by persiste {@link Personagem}
	 * @param personagem - new entity or update {@link Personagem}
	 * @return Personagem -{@link Personagem}
	 * @throws HouseInexistenteException 
	 */
	public Personagem save(PersonagemDto personagemDto) throws PersonagemException {
		
		if (isHouseValid(personagemDto.getHouse())) {
			Personagem personagem = PersonagemMapper.MAPPER.personagemDtoToPersonagem(personagemDto);
			return personagemRepository.save(personagem);

		} else {
			throw new HouseInexistenteException(
					String.format("House %s não existe no cadastro parceiro", personagemDto.getHouse()));
		}
		
	}
	
	public Personagem update(Long idPersonagem, PersonagemDto personagemDto) throws PersonagemException {
		
		if (!isHouseValid(personagemDto.getHouse())) {
			throw new HouseInexistenteException(
					String.format("House %s não existe no cadastro do parceiro", personagemDto.getHouse()));
		}

		return personagemRepository.findById(idPersonagem).map(personagem -> {
			personagem = PersonagemMapper.MAPPER.personagemDtoToPersonagem(personagemDto);
			personagem.setId(idPersonagem);
			return personagemRepository.save(personagem);
		}).orElseThrow(() -> new PersonagemInexistenteException(
				String.format("Personagem %s com identificador %s não existe na base", personagemDto.getName(),
						idPersonagem)));
		
	}
	
	/**
	 * @param personagem
	 * @throws PersonagemException 
	 */
	public void delete(Long idPersonagem) throws PersonagemException {
		
		personagemRepository.findById(idPersonagem).map(personagem -> {
			personagemRepository.delete(personagem);
			return Void.TYPE;
		}).orElseThrow(() -> new PersonagemInexistenteException(
				String.format("Personagem com identificador %s não existe na base", idPersonagem)));
	}
	
	/**
	 * @param idPersonagem
	 * @return
	 */
	public Optional<Personagem> findPersonagemById(Long idPersonagem) {
		return personagemRepository.findById(idPersonagem);
	}
	
	
	/**
	 * @return
	 */
	public List<Personagem> listPersonagens() {
		return (List<Personagem>) personagemRepository.findAll();
	}
	
	/**
	 * Method responsible for validating if the home exists in the integration system
	 * @param personagem
	 * @return
	 */
	private boolean isHouseValid(String house) {
		return houseIntegracaoService.validarIdHouse(house);
	}
}
