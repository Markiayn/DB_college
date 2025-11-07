// ua.markiyan.sonara.security.JwtService
package ua.markiyan.sonara.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private final Key key;
    private final long expMs;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.exp-ms:86400000}") long expMs) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)); // 256-bit Base64
        this.expMs = expMs;
    }

    public String generate(String subject, Map<String,Object> claims){
        var now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValid(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) { return false; }
    }

    public String getSubject(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
