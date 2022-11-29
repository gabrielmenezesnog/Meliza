package com.gmnsystems.meliza.config.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {

  // Classe especializada em gerar Tokens JWT para o acesso
  // do usuário. Possui data de início e data de expiração
  // baseada nos parâmetros criados em SecurityConstants
  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expirationDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

    // Geração do token. Utilizando da dependência do JWT,
    // é possível "buildar" um token JWT informando username,
    // data de início, data de expiração, assinatura de algoritmo
    // e a secret key
    String token = Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
        .compact();

    return token;
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(SecurityConstants.JWT_SECRET)
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
      return true;
    } catch (Exception ex) {
      throw new AuthenticationCredentialsNotFoundException("Credentials have expired or are incorrect");
    }
  }
}
