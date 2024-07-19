package it.luigiVasile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="utente_meteo")
public class UtenteMeteo {
	
	@Id
	@Column(name="utente_id")
	private int IdU;
	
	@Id
	@Column(name="meteo_id")
	private int IdM;


	public int getIdU() {
		return IdU;
	}


	public void setIdU(int idU) {
		IdU = idU;
	}


	public int getIdM() {
		return IdM;
	}


	public void setIdM(int idM) {
		IdM = idM;
	}
	


}
