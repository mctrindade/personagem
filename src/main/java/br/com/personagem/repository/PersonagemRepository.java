package br.com.personagem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.personagem.model.Personagem;

@Repository
public interface PersonagemRepository extends CrudRepository<Personagem, Long>{

}
