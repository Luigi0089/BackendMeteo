package it.luigiVasile.service;

import java.util.List;

import it.luigiVasile.dto.UtenteLoginResponseDto;
import it.luigiVasile.dto.ModificaPasswordDto;
import it.luigiVasile.dto.UtenteLoginRequestDto;
import it.luigiVasile.dto.UtenteRegistrazioneDto;
import it.luigiVasile.model.Utente;

public interface UtenteService {
	
	    void UtenteRegistrazione(UtenteRegistrazioneDto utente);
		
		Utente getUtenteById(int id);
		
		boolean UtenteModificaPassword(ModificaPasswordDto utente);
		
		boolean existUtenteByEmail( String email);
		
		boolean existUtenteByEmailAndPassword(String email, String password);

		Utente getUtenteByEmail(String email);

		List<Utente> getUtenti();

		public UtenteLoginResponseDto issueToken( String email);
		
		public boolean Login(UtenteLoginRequestDto utenteLoginRequestDto);
		
		boolean deleteUtente(UtenteLoginRequestDto utenteLoginRequestDto);
		
}
