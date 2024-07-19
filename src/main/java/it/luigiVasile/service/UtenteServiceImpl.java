package it.luigiVasile.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.luigiVasile.model.Ruolo;
import it.luigiVasile.dao.UtenteDao;
import it.luigiVasile.dto.ModificaPasswordDto;
import it.luigiVasile.dto.UtenteLoginRequestDto;
import it.luigiVasile.dto.UtenteLoginResponseDto;
import it.luigiVasile.dto.UtenteRegistrazioneDto;
import it.luigiVasile.model.Utente;

@Service
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteDao utenteDao;

	@Override
	public void UtenteRegistrazione(UtenteRegistrazioneDto utente) {
		
		
			String sha256hex=DigestUtils.sha256Hex(utente.getPassword());
			
			utente.setPassword(sha256hex);
			
			Utente utente_nuovo=new Utente();
			utente_nuovo.setNome(utente.getNome());
			utente_nuovo.setCognome(utente.getCognome());
			utente_nuovo.setEmail(utente.getEmail());
			utente_nuovo.setPassword(utente.getPassword());
			utenteDao.save(utente_nuovo);
		

	}

	@Override
	public Utente getUtenteById(int id) {
Optional<Utente> utenteOptionalDb= utenteDao.findById(id);
		
		if(!utenteOptionalDb.isPresent()) {
			return null;
	}
		
		return utenteOptionalDb.get();
	}

	@Override
	public boolean UtenteModificaPassword(ModificaPasswordDto utente) {
		Utente utente1=new Utente();
		utente1.setEmail(utente.getEmail());
		utente1.setPassword(DigestUtils.sha256Hex(utente.getVecchiaPassword()));
		Utente utenteDb= utenteDao.findByEmailAndPassword(utente1.getEmail(),utente1.getPassword());
		if(utenteDb!=null) {
			
			utenteDb.setPassword(DigestUtils.sha256Hex(utente.getPassword()));
			utenteDao.save(utenteDb);
			return true;
		}
		return false;
	}

	@Override
	public boolean existUtenteByEmail(String email) {
		return utenteDao.existsByEmail(email);
	}

	@Override
	public Utente getUtenteByEmail(String email) {
		return utenteDao.findByEmail(email);
	}

	@Override
	public List<Utente> getUtenti() {
		return (List<Utente>) utenteDao.findAll();
	}

	@Override
	public UtenteLoginResponseDto issueToken(String email) {

		byte[] secret= "LuigiFrancescoVasileTestFinale19072024".getBytes();
		Key key= Keys.hmacShaKeyFor(secret);
		
		Utente InformazioniUtente=utenteDao.findByEmail(email);

	    if (InformazioniUtente == null) {
	   
	        throw new IllegalArgumentException("Utente non trovato");
	    }
		Map<String, Object> map= new HashMap<>();
		
		map.put("nome", "InformazioniUtente.getNome()");
		map.put("cognome", "InformazioniUtente.getCognome()");
		map.put("email", "InformazioniUtentee.getEmail");
		
		
		UtenteLoginResponseDto token=new UtenteLoginResponseDto();
		List<String> ruoli=new ArrayList<>();
		List<Ruolo> forech=InformazioniUtente.getRuoli();
		List<Integer> IdRuolo=new ArrayList<>();
		
		for(Ruolo ruolo : forech ) {
			
		
			ruoli.add(ruolo.getTipologia().name());
			if(ruolo.getTipologia().name()=="Admin") {
				IdRuolo.add(1);
			}
		}
		  
		
		map.put("ruoli", ruoli);
		Date creation= new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		
		String tokenJwts =Jwts.builder()
				.setClaims(map)
				.setIssuer("http://localhost:8080")
				.setExpiration(end)
				.signWith(key)
				.compact();
		
		
		token.setToken(tokenJwts);
		token.setTokenCreationTime(creation);
		token.setTtl(end);
		token.setRuoli(IdRuolo);
		return token;
	}

	@Override
	public boolean Login(UtenteLoginRequestDto utenteLoginRequestDto) {
		
		String password = DigestUtils.sha256Hex(utenteLoginRequestDto.getPassword());
		
		Utente utente = utenteDao.findByEmailAndPassword(utenteLoginRequestDto.getEmail(), password);
		
		return utente!=null?true:false;
	}

	@Override
	public boolean existUtenteByEmailAndPassword(String email, String password) {
	if(utenteDao.existsByEmailAndPassword(email, password)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteUtente(UtenteLoginRequestDto utenteLoginRequestDto) {
		
		Utente utente=utenteDao.findByEmailAndPassword(utenteLoginRequestDto.getEmail(), utenteLoginRequestDto.getPassword());
		utenteDao.delete(utente);
	
		return false;
	}

	

}
