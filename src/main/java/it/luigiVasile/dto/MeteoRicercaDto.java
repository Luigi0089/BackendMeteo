package it.luigiVasile.dto;

public class MeteoRicercaDto {
	
	private String nome;
	
	private float latitudine;
	
	private float longitudine;

	
	public float getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(float latitudine) {
		this.latitudine = latitudine;
	}

	public float getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(float longitudine) {
		this.longitudine = longitudine;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
