package com.example.demo.security.providers;

import com.example.demo.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTProvider {
    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    @Value("${ACCESS_TOKEN_EXPIRATION}")
    private long ACCESS_JWT_EXPIRATION;

    @Value("${REFRESH_TOKEN_EXPIRATION}")
    private long REFRESH_JWT_EXPIRATION;

    public String generateAccessToken(UserEntity userEntity) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(Long.toString(userEntity.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String generateTokenValidWithin(UserEntity userEntity, int min) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + min * 60 * 1000);

        return Jwts.builder()
                .setSubject(Long.toString(userEntity.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String generateRefreshToken(UserEntity userEntity) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(Long.toString(userEntity.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public int getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);

        return true;
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
        } catch (Exception exception) {
            return false;
        }

        return true;
    }
}
