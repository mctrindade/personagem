package br.com.personagem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.personagem.enums.PatrounsEnum;
import br.com.personagem.enums.RolesEnum;
import lombok.Data;

@Entity
@Table(name = "Personagem")
@Data
public class Personagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 500)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RolesEnum role;
	
	@Column(nullable = false)
	private String school;
	
	@Column(nullable = false)
	private String house;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PatrounsEnum patronus;
}
