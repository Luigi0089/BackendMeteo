package it.luigiVasile.jwt;

import java.io.IOException;
import java.security.Key;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JWTTokenNeededFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;
	

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		Secured annotetedRole =	resourceInfo.getResourceMethod().getAnnotation(Secured.class);
		JWTTokenNeeded jwtAnnotation=resourceInfo.getResourceMethod().getAnnotation(JWTTokenNeeded.class);
		
		
		if(annotetedRole == null) {
			annotetedRole = resourceInfo.getResourceClass().getAnnotation(Secured.class) ;
			
			
		}
		
		if(jwtAnnotation==null) {
			jwtAnnotation = resourceInfo.getResourceClass().getAnnotation(JWTTokenNeeded.class) ;
		
		}
		
		if(annotetedRole == null && jwtAnnotation==null ) {
			
			return;
		}
		
		String autorizzazione = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		
	
		if(autorizzazione==null || !autorizzazione.startsWith("Bearer")) {
			
		throw new NotAuthorizedException("Autorizzazione non fornita");
		
		}
		
		String token= autorizzazione.substring("Bearer".length()).trim();
		
		
		try {
			byte[] secret= "LuigiFrancescoVasileTestFinale19072024".getBytes();
		
		Key key= Keys.hmacShaKeyFor(secret);
		
		Jws<Claims> claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		if(annotetedRole==null) {
			System.out.println("Sono nell'if");
			return;
		}
		
		Claims body= claims.getBody();
		List<String> rolesToken=body.get("ruoli", List.class);
		
		Boolean hasRole= false;
		
		for (String role: rolesToken) {
			if(role.equals(annotetedRole.role())) {
				
				hasRole=true;
			}
			
		    }
		
		if(!hasRole) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
		} catch (Exception e) {
			e.printStackTrace();
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}


	

}
