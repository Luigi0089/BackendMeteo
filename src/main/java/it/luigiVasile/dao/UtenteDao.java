package it.luigiVasile.dao;

import org.springframework.data.repository.CrudRepository;

import it.luigiVasile.model.Utente;

public interface UtenteDao extends CrudRepository<Utente, Integer> {
	
    boolean existsByEmail(String email);
    
    boolean existsByEmailAndPassword(String email, String Password);
	
	Utente findByEmail(String email);

	Utente findByEmailAndPassword(String email, String password);

}
