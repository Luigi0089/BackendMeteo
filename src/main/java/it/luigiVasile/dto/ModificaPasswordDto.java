package it.luigiVasile.dto;

import jakarta.validation.constraints.Pattern;

public class ModificaPasswordDto {
	
	@Pattern(regexp="[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,8}",message= "mail non valida")
	private String email;
	
	private String vecchiaPassword;
	
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVecchiaPassword() {
		return vecchiaPassword;
	}

	public void setVecchiaPassword(String vecchiaPassword) {
		this.vecchiaPassword = vecchiaPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
