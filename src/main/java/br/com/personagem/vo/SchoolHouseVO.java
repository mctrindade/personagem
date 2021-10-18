package br.com.personagem.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolHouseVO {

	private String id;
	private String name;
	private String headOfHouse;
	private List<String> values;
	private List<String> colors;
	private String school;
	private String mascot;
	private String houseGhost;
	private String founder;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadOfHouse() {
		return headOfHouse;
	}
	public void setHeadOfHouse(String headOfHouse) {
		this.headOfHouse = headOfHouse;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	public List<String> getColors() {
		return colors;
	}
	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMascot() {
		return mascot;
	}
	public void setMascot(String mascot) {
		this.mascot = mascot;
	}
	public String getHouseGhost() {
		return houseGhost;
	}
	public void setHouseGhost(String houseGhost) {
		this.houseGhost = houseGhost;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	
}
