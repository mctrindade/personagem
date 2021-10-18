package br.com.personagem.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseVO {
	
	List<SchoolHouseVO> houses;

	public List<SchoolHouseVO> getHouses() {
		return houses;
	}

	public void setHouses(List<SchoolHouseVO> houses) {
		this.houses = houses;
	}

	
	
}
