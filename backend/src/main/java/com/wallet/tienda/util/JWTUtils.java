package com.wallet.tienda.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JWTUtils {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private int timeExpiration;

    /**
     * Extrae el email del token
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Generar token de acceso
     * @param claims
     * @param subject
     * @return token
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeExpiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Validar el token de acceso
     * @param token
     * @param user
     * @return boolean
     */
    public boolean isTokenValid(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()));
    }

    /**
     * Obtener el username del token
     * @param token
     * @return username
     */
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Obtener la fecha de expiracion del token
     * @param token
     * @return date
     */
    public Date getExpirationDate(String token){
        return getClaim(token, Claims::getIssuedAt);
    }

    /**
     * Devuelve true si la fecha del token expiro, sino devuelve false
     * @param token
     * @return boolean
     */
    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDate(token);
        return expirationDate.before(new Date());
    }

    /**
     * Obtener un solo claim
     * @param token
     * @param claimsTFunction
     * @return claim
     * @param <T>
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    /**
     * Obtener todos los claims del token
     * @param token
     * @return claims
     */
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Obtener firma del token
     * @return
     */
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}