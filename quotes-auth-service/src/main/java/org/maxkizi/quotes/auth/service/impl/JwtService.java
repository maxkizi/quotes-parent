package org.maxkizi.quotes.auth.service.impl;

import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final Clock clock = DefaultClock.INSTANCE;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expiration;

    public String buildJwtToken(UserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<>();
        final Date createdDate = clock.now();
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(createdDate)
                .setExpiration(new Date(createdDate.getTime() + expiration * 1000 * 60))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
