package ua.markiyan.sonara.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key;
    private final long expMs;

    public JwtUtil(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.exp-ms}") long expMs) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("JWT secret not configured (app.jwt.secret)");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expMs = expMs;
    }

    public String generateToken(String username, Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parseToken(String token) throws JwtException {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public String usernameFromToken(String token) {
        return parseToken(token).getBody().getSubject();
    }
}

