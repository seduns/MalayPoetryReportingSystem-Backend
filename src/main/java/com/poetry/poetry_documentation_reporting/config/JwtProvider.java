package com.poetry.poetry_documentation_reporting.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtProvider {

    private final SecretKey key;
    private final long jwtExpirationsMs;
    private final long refreshExpirationsMs;

    public JwtProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long jwtExpirationsMs, @Value("${jwt.refreshExpiration}") long refreshExpirationsM) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationsMs = jwtExpirationsMs;
        this.refreshExpirationsMs = refreshExpirationsM;
    }

    // generete access token
    public String generateAccessToken(Authentication auth) {
        String roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(auth.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationsMs))
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // generete refresh token
    public String generateFreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationsMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // claim
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract email(subject) and roles
    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getRolesFromToken(String token) {
        return String.valueOf(getClaims(token).get("roles"));
    }

    // validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT unsupported: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("JWT malformed: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Invalid signature: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument token: " + e.getMessage());
        }
        return false;
    }
}
