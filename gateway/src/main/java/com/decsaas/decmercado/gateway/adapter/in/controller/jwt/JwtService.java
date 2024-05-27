package com.decsaas.decmercado.gateway.adapter.in.controller.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Collectors;

@Component
public class JwtService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public JwtService(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public String generatorToke(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 60L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(""));

        var claim = JwtClaimsSet.builder()
                .issuer("spring-security-jwt")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scopes", scopes)
                .build();
        return encoder.encode(JwtEncoderParameters.from(claim)).getTokenValue();
    }

    public Jwt isTokenValid(String token) {
        try {
            return decoder.decode(token);
        } catch (JwtException ex) {
            return null;
        }
    }
}
