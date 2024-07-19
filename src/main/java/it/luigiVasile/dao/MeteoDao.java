package it.luigiVasile.dao;

import org.springframework.data.repository.CrudRepository;

import it.luigiVasile.model.Meteo;

public interface MeteoDao extends CrudRepository<Meteo, Integer> {
	
	boolean existsByNome(String nome);

}
