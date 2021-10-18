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
	 * {@link PersonagemRepository} - repository responsible by provide {@link Personagem}
	 */
	private final PersonagemRepository personagemRepository;
	
	/**
	 * {@link HouseIntegracaoService} - service integration the House
	 */
	private final HouseIntegracaoService houseIntegracaoService;
	
	/**
	 * Constructor class {@link PersonagemService}
	 * @param personaRepository - repository responsible by provide {@link Personagem}
	 * @param houseIntegracaoService - service integration the House
	 */
	@Autowired
	public PersonagemService(PersonagemRepository personaRepository, HouseIntegracaoService houseIntegracaoService) {
		this.personagemRepository = personaRepository;
		this.houseIntegracaoService = houseIntegracaoService;
	}
	
	
	/**
	 * method responsible by persiste {@link Personagem}
	 * @param personagemDto - new personagem {@link PersonagemDto}
	 * @return Personagem - entity {@link Personagem} persisted
	 * @throws HouseInexistenteException 
	 */
	public Personagem save(PersonagemDto personagemDto) throws PersonagemException {
		//check if the house exists on system integration, if there is personagem is persisted
		if (isHouseValid(personagemDto.getHouse())) {
			Personagem personagem = PersonagemMapper.MAPPER.personagemDtoToPersonagem(personagemDto);
			return personagemRepository.save(personagem);

		} else {
			throw new HouseInexistenteException(
					String.format("House %s não existe no cadastro parceiro", personagemDto.getHouse()));
		}
		
	}
	
	/**
	 * Method responsible for update the personagem
	 * @param idPersonagem - identifier of {@link Personagem}
	 * @param personagemDto - dto of {@link PersonagemDto}
	 * @return Personagem - entity {@link Personagem} updated
	 * @throws PersonagemException
	 */
	public Personagem update(Long idPersonagem, PersonagemDto personagemDto) throws PersonagemException {
		//check if the house exists on system integration
		if (!isHouseValid(personagemDto.getHouse())) {
			throw new HouseInexistenteException(
					String.format("House %s não existe no cadastro do parceiro", personagemDto.getHouse()));
		}
		//check if the personagem exists in the database, if there is updated
		//If it doesn't throw an error
		return personagemRepository.findById(idPersonagem).map(personagem -> {
			personagem = PersonagemMapper.MAPPER.personagemDtoToPersonagem(personagemDto);
			personagem.setId(idPersonagem);
			return personagemRepository.save(personagem);
		}).orElseThrow(() -> new PersonagemInexistenteException(
				String.format("Personagem %s com identificador %s não existe na base", personagemDto.getName(),
						idPersonagem)));
		
	}
	
	/**
	 * Method responsible for delete the personagem
	 * @param idPersonagem - identifier of {@link Personagem}
	 * @throws PersonagemException 
	 */
	public void delete(Long idPersonagem) throws PersonagemException {
		//check if the personagem exists in the database, if there is delete. 
		//If it doesn't throw an error
		personagemRepository.findById(idPersonagem).map(personagem -> {
			personagemRepository.delete(personagem);
			return Void.TYPE;
		}).orElseThrow(() -> new PersonagemInexistenteException(
				String.format("Personagem com identificador %s não existe na base", idPersonagem)));
	}
	
	/**
	 * Method responsible for get the personagem
	 * @param idPersonagem - identifier of {@link Personagem}
	 * @return {@link Personagem} - entity of {@link Personagem}
	 */
	public Optional<Personagem> findPersonagemById(Long idPersonagem) {
		return personagemRepository.findById(idPersonagem);
	}
	
	
	/**
	 * Method responsible for list all personagens
	 * @return {@link List<Personagem>} list of Personagens
	 */
	public List<Personagem> listPersonagens() {
		return (List<Personagem>) personagemRepository.findAll();
	}
	
	/**
	 * Method responsible for validating if the home exists in the integration system
	 * @param house - name the house
	 * @return
	 */
	private boolean isHouseValid(String house) {
		return houseIntegracaoService.validarIdHouse(house);
	}
}
