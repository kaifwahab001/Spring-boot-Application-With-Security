package com.example.springmvc.practise.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private String SecretKey = "aofasfhsofnwownfns938n3fndkfnsaofjsnfasnasfsmfjsnifons";

    public String genrerateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();

    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8));
    }
}
