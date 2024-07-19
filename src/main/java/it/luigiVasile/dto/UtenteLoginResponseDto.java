package it.luigiVasile.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UtenteLoginResponseDto {

	 private String token;
	   
	   private Date ttl;
	   
	   private Date tokenCreationTime;
	   
	   private List<Integer> ruoli = new ArrayList<>();

	
	public List<Integer> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Integer> ruoli) {
		this.ruoli = ruoli;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTtl() {
		return ttl;
	}

	public void setTtl(Date ttl) {
		this.ttl = ttl;
	}

	public Date getTokenCreationTime() {
		return tokenCreationTime;
	}

	public void setTokenCreationTime(Date tokenCreationTime) {
		this.tokenCreationTime = tokenCreationTime;
	}
}
