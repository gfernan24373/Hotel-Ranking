package com.api.auth.security;

import com.api.auth.dto.RequestDTO;
import com.api.auth.model.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider {

    private Key secret;

    @Autowired
    private RouteValidation routeValidation;

    @Value("${ttl.secret}")
    private int endToken;

    @PostConstruct // create encrypted Algorithm
    protected void init() {
        byte[] apiKeySecretB = new byte[64];
        new SecureRandom().nextBytes(apiKeySecretB); // create random bytes
        secret = Keys.hmacShaKeyFor(apiKeySecretB); //
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch(Exception e) {
            log.error("Error :" + e.getMessage());
            return "bad token";
        }
    }

    public String createToken(AuthUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        claims.put("company", "AulaMatriz");
        LocalDateTime now = LocalDateTime.now();

        return Jwts
                .builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(this.converToLocalDateTimeToDate(now))
                .expiration(this.converToLocalDateTimeToDate(now.plusHours(endToken)))
                .signWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .compact();
    }

    //Convert localDateTime to Date
    private Date converToLocalDateTimeToDate(LocalDateTime localDateTime) {

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private boolean isAdmin(String token) {
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role").equals("admin");
    }

    public boolean validate(String token, RequestDTO requestDTO) {
        try {
            Jwts
                    .parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token);
        } catch(Exception e) {
            return false;
        }

        if(!isAdmin(token) && routeValidation.isAdmin(requestDTO) ){
            return false;
        }
        return true;
    }
}
