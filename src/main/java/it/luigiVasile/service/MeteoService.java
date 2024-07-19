package it.luigiVasile.service;

import java.util.List;

import it.luigiVasile.dto.MeteoRicercaDto;
import it.luigiVasile.dto.MeteoRicercaRisposraDto;
import it.luigiVasile.dto.MeteoTemperatura;
import it.luigiVasile.dto.UtenteLoginRequestDto;

public interface MeteoService {
	
	List<MeteoRicercaRisposraDto>ricerca(MeteoRicercaDto paese);
	
	MeteoTemperatura temperatura(MeteoRicercaDto paese);
	
	void SalvaPaese(MeteoRicercaRisposraDto paese, UtenteLoginRequestDto utente);

}
