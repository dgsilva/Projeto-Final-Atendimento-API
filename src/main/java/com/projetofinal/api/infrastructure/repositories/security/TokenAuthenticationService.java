package com.projetofinal.api.infrastructure.repositories.security;

import java.util.function.Function;

import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class TokenAuthenticationService {
	
	//Método para retornar a identificação do usuário do token(cliente)
	
		public String getUserFromToken(String token) {
			return getContentFromToken(token, Claims::getSubject);
		}
		
		//Método para ler o conteudo do token
		private <T> T getContentFromToken(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = Jwts.parser().setSigningKey(TokenAuthenticationFilter.SECRET.getBytes())
					.parseClaimsJws(token).getBody();
			return claimsResolver.apply(claims);
		}

}
