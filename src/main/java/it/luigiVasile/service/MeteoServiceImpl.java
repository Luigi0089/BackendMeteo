package it.luigiVasile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import it.luigiVasile.dao.MeteoDao;
import it.luigiVasile.dao.UtenteDao;
import it.luigiVasile.dto.MeteoRicercaDto;
import it.luigiVasile.dto.MeteoRicercaRisposraDto;
import it.luigiVasile.dto.MeteoTemperatura;
import it.luigiVasile.dto.UtenteLoginRequestDto;
import it.luigiVasile.model.Meteo;
import it.luigiVasile.model.Utente;
import reactor.core.publisher.Mono;

@Service
public class MeteoServiceImpl implements MeteoService {

	@Autowired
	public MeteoDao meteoDao;
	 
	@Autowired
	public UtenteDao utenteDao;
	        
	@Override
	public List<MeteoRicercaRisposraDto> ricerca(MeteoRicercaDto paese) {
		
		  WebClient webClient= WebClient.builder()
	             .baseUrl("https://geocoding-api.open-meteo.com/v1") // URL base dell'API meteorologica
	             .build();
		 
		JsonNode json=	webClient.get()
				  .uri(uriBuilder -> uriBuilder
						    .path("/search")
						    .queryParam("name", paese.getNome())
						    .build())
						  .retrieve()
						  .bodyToMono(JsonNode.class)
						  .onErrorResume(e -> Mono.empty())
						  .block();
		
		json=json.get("results");
		
		List<MeteoRicercaRisposraDto> paesi = new ArrayList<>();
        for(JsonNode result : json) {
        	MeteoRicercaRisposraDto trovato =new MeteoRicercaRisposraDto();
        	trovato.setId(result.get("id").intValue());
			
		trovato.setNome(result.get("name").textValue());
		
		trovato.setLatitudine(result.get("latitude").doubleValue());
		
		trovato.setLongitudine(result.get("longitude").doubleValue());
		
		trovato.setAltitudine(result.get("elevation").floatValue());
		
		paesi.add(trovato);
        	
            }
        
        return  paesi;
        }
		
				
		

					

	@Override
	public MeteoTemperatura temperatura(MeteoRicercaDto paese) {
		
		 WebClient webClient= WebClient.builder()
	             .baseUrl("https://api.open-meteo.com/v1") // URL base dell'API meteorologica
	             .build();
		 
		System.out.println("Sono nel Service");
		MeteoRicercaRisposraDto trovato=this.ricerca(paese).get(0);
		System.out.println("trovato: "+trovato.getLatitudine()+ " "+ trovato.getLongitudine());
		JsonNode json=	webClient.get()
				  .uri(uriBuilder -> uriBuilder
						    .path("/forecast")
						    .queryParam("latitude", trovato.getLatitudine())
						    .queryParam("longitude", trovato.getLongitudine())
						    .queryParam("hourly", "temperature_2m")
						    .build())
						  .retrieve()
						  .bodyToMono(JsonNode.class)
						  .onErrorResume(e -> Mono.empty())
						  .block();
		
		
		System.out.println("jsono: "+json.toPrettyString());
		MeteoTemperatura finale=new MeteoTemperatura();
		
		List<Float> temperature=new ArrayList();
		
		finale.setNome(trovato.getNome());
		for(JsonNode result : json.get("hourly").get("temperature_2m")) {
			temperature.add(result.floatValue());
			System.out.println("result: "+result);
		}
		
		
		float numeratore=0;
		for(Float item: temperature) {
			
			numeratore+=item;
			
		}
		
		finale.setTemperatura(numeratore/(temperature.size()));
		
		return finale;
	}






	@Override
	public void SalvaPaese(MeteoRicercaRisposraDto paese, UtenteLoginRequestDto utente) {
		
		Meteo nuovo=new Meteo();
		Utente loggato=utenteDao.findByEmailAndPassword(utente.getEmail(), utente.getPassword());
		
		nuovo.setId(paese.getId());
		nuovo.setNome(paese.getNome());
		nuovo.setElevation(paese.getAltitudine());
		nuovo.setLatitudine(paese.getLatitudine());
		nuovo.setLongitudine(paese.getLongitudine());
		nuovo.getUtenti().add(loggato);
		loggato.getMetei().add(nuovo);
		
		utenteDao.save(loggato);
		meteoDao.save(nuovo);
	}
	
	

}
