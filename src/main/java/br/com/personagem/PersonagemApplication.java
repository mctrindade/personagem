package br.com.personagem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PersonagemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonagemApplication.class, args);
	}

}
