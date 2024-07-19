package it.luigiVasile.dto;

import org.springframework.web.bind.annotation.RequestBody;

public class MeteoSalvaDto {
	
	private MeteoRicercaRisposraDto paese;
	
	private UtenteLoginRequestDto utente;

	public MeteoRicercaRisposraDto getPaese() {
		return paese;
	}

	public void setPaese(MeteoRicercaRisposraDto paese) {
		this.paese = paese;
	}

	public UtenteLoginRequestDto getUtente() {
		return utente;
	}

	public void setUtente(UtenteLoginRequestDto utente) {
		this.utente = utente;
	}

}
