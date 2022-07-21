package com.javatpoint.aspect;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class JwtTokenUtil implements Serializable {

    public String generateToken(String clientId){
        return Jwts.builder().setSubject(clientId
        + UUID.randomUUID().toString()
        ).compact();
    }
}
