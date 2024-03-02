package com.example.exame2api.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch(ExpiredJwtException e){
            log.error(" Token expirado: {}",e.getMessage());
        }
        catch(SignatureException e){
            log.error(" Token invalido: {}",e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJetToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret)
        .parseClaimsJws(token).getBody().getSubject();
    }
}
