package it.luigiVasile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.luigiVasile.dto.MeteoRicercaDto;
import it.luigiVasile.dto.MeteoRicercaRisposraDto;
import it.luigiVasile.dto.MeteoSalvaDto;
import it.luigiVasile.dto.MeteoTemperatura;
import it.luigiVasile.dto.UtenteLoginRequestDto;
import it.luigiVasile.service.MeteoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import org.springframework.web.bind.annotation.RequestBody;

@Path("/meteo")
public class MeteoController {
	
	@Autowired
	private MeteoService meteoService;
	
	@GET
	@Path("/nome/{paese}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPaeseByName(@PathParam("paese") String nome) {
		try {
			MeteoRicercaDto paese=new MeteoRicercaDto();
			paese.setNome(nome);
			List<MeteoRicercaRisposraDto> paesi= meteoService.ricerca(paese);
			return Response.status(Response.Status.OK).entity(paesi).build();
		}catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
			
		}
	}
	
	@GET
	@Path("/temperatura/{paese}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPaesewhitTemperature(@PathParam("paese") String nome) {
		try {
			MeteoRicercaDto paese=new MeteoRicercaDto();
			paese.setNome(nome);
			MeteoTemperatura trovato= meteoService.temperatura(paese);
			return Response.status(Response.Status.OK).entity(trovato).build();
		}catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
			
		}
	}
	
	@POST
	 @Path("/salva")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response salva(@RequestBody MeteoSalvaDto Body ) {
		
		MeteoRicercaRisposraDto paese=Body.getPaese();
		UtenteLoginRequestDto utente=Body.getUtente();
		System.out.println(paese.getNome()+" "+utente.getEmail());
		
		try {
			meteoService.SalvaPaese(paese, utente);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	

}
