package org.example.projekt_ztpai.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private String secret = "supermegasecretkeythatisatleast32charslongwowsocool";
    private SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    private JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
                .signWith(secretKey)
                .compact();
    }
    public String extractUsername(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().getSubject();
        //return Jwts.parser().setSigningKey(secret)
        //      .parseClaimsJws(token).getBody().getSubject();
    }
    public String extractRole(String token) {
        return jwtParser
                .parseSignedClaims(token).getPayload().get("role",
                        String.class);
    }
    public boolean validateToken(String token) {
        try {
            jwtParser.parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}