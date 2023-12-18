package com.branow.memoweb.service.impl;

import com.branow.memoweb.model.User;
import com.branow.memoweb.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;


    public boolean hasSubject(String token, String subject) {
        return getSubject(token).equals(subject);
    }

    @Override
    public boolean isExpired(String token) {
        return getExpirationDateTime(token).isBefore(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getExpirationDateTime(String token) {
        token = token.replaceFirst("bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        return LocalDateTime.ofInstant(Objects.requireNonNull(jwt.getExpiresAt()), ZoneId.systemDefault());
    }

    public String getSubject(String token) {
        token = token.replaceFirst("bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public String generateJwt(User user) {
        return generateJwt(user, LocalDateTime.now().plus(5, ChronoUnit.DAYS));
    }

    @Override
    public String generateVerificationJwt(User user) {
        return generateJwt(user, LocalDateTime.now().plus(10, ChronoUnit.MINUTES));
    }

    private String generateJwt(User user, LocalDateTime expiration) {
        Instant now = Instant.now();
        Instant expired = expiration.atZone(ZoneId.systemDefault()).toInstant();

        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expired)
                .subject(user.getUserId().toString())
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
