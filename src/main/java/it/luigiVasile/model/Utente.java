package it.luigiVasile.model;

import java.util.ArrayList;
import java.util.List;


import it.luigiVasile.model.Ruolo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Pattern(regexp="[a-zA-Z\\èàùìò]{1,50}", message= "nome con caratteri non ammessi")
	@Column(name="Nome")
	private String nome;
	
	@Pattern(regexp="[a-zA-Z\\èàùìò]{1,50}", message= "nome con caratteri non ammessi")
	@Column(name="Cognome")
	private String cognome;
	
	@Pattern(regexp="[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,8}",message= "mail non valida")
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	
	@ManyToMany(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinTable
	(
			name="utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID" ),
			 
			inverseJoinColumns = @JoinColumn(name ="ruolo_id", referencedColumnName = "ID")
			)
	private List<Ruolo> ruoli = new ArrayList<>();
	
	
	@ManyToMany(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@JoinTable
	(
			name="utente_meteo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID" ),
			 
			inverseJoinColumns = @JoinColumn(name ="meteo_id", referencedColumnName = "ID")
			)
	private List<Meteo> metei = new ArrayList<>();
	
	public List<Meteo> getMetei() {
		return metei;
	}

	public void setMetei(List<Meteo> metei) {
		this.metei = metei;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setRuoli(List<Ruolo> ruoli) {
		  this.ruoli=ruoli;
	  }
	  
	  public List<Ruolo> getRuoli(){
		  return ruoli;
	  }

	
}