package com.branow.memoweb.service;

import com.branow.memoweb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String getSubject(String token) {
        token = token.replaceFirst("bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public String generateJwt(User user) {
        Instant now = Instant.now();
        Instant expired = LocalDateTime.now().plus(10, ChronoUnit.MINUTES)
                .atZone(ZoneId.systemDefault()).toInstant();

        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expired)
                .subject(user.getEmail())
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateJwt(Authentication auth) {
        Instant now = Instant.now();
        Instant expired = LocalDateTime.now().plus(10, ChronoUnit.MINUTES)
                .atZone(ZoneId.systemDefault()).toInstant();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expired)
                .subject(auth.getName())
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
