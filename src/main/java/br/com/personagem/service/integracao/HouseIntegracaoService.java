package br.com.personagem.service.integracao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.personagem.exception.HouseIntegracaoException;
import br.com.personagem.vo.HouseVO;
import br.com.personagem.vo.SchoolHouseVO;

@Service
public class HouseIntegracaoService {
	
	/**
	 * fiel constant name 
	 */
	private static final String HEADER_APIKEY = "apikey";
	
	@Value("${url.get.houses}")
	private String urlHouses;
	
	@Value("${api.key}")
	private String apiKey;
	
	/**
	 * Method responsible for checking if the house exists
	 * @param idHouse - identifier of {@link SchoolHouseVO}
	 * @return the {@link Boolean} 
	 * @throws HouseIntegracaoException
	 */
	public boolean isValidHouseByIdHouse(String idHouse) throws HouseIntegracaoException {
	   	Optional<HouseVO> houseRetorno = callHouseIntegracao();
	    return houseRetorno
			.map(HouseVO::getHouses)
			.map(lista -> lista.stream()
					.filter(school -> school.getId().equals(idHouse))
					.findAny().isPresent()).get();
	}
	
	
	/**
	 * Method responsible for get the {@link SchoolHouseVO}
	 * @param idHouse
	 * @return {@link Optional<SchoolHouseVO>} - entity of {@link SchoolHouseVO}
	 * @throws HouseIntegracaoException
	 */
	public Optional<SchoolHouseVO> getSchoolHouseByIdHouse(String idHouse) throws HouseIntegracaoException {
	   	Optional<HouseVO> houseRetorno = callHouseIntegracao();
	    return houseRetorno
			.map(HouseVO::getHouses)
			.map(lista -> lista.stream()
					.filter(school -> school.getId().equals(idHouse))
					.findAny()).get();
	}
	
	
	/**
	 * Method responsible for execute call with the integration
	 * @return {@link Optional<HouseVO>} - entity of {@link HouseVO}
	 * @throws HouseIntegracaoException
	 */
	private Optional<HouseVO> callHouseIntegracao() throws HouseIntegracaoException {
		RestTemplate restTemplate = new RestTemplate();
		// make an HTTP GET request with headers
		ResponseEntity<HouseVO> response = restTemplate.exchange(urlHouses, HttpMethod.GET, new HttpEntity<String>(getHttpHeadersHouse()),
				HouseVO.class);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			return Optional.ofNullable(response.getBody());
		} else {
			throw new HouseIntegracaoException(
					String.format("Integration system unavailable, try later! Status %s", response.getStatusCode()));
		}
	}


	/**
	 * Method responsible for configuring {@link HttpHeaders}
	 * @return {@link HttpHeaders}
	 */
	private HttpHeaders getHttpHeadersHouse() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HEADER_APIKEY, apiKey);
		return headers;
	}

}
