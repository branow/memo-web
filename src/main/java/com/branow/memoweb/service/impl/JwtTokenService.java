package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.JwtTokenIllegalSubjectException;
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


    public boolean hasUserId(String token, Integer userId) {
        return hasSubject(token, userId.toString());
    }

    public boolean hasSubject(String token, String subject) {
        return getSubject(token).equals(subject);
    }

    public Integer getUserId(String token) {
        try {
            return Integer.parseInt(getSubject(token));
        } catch (NumberFormatException e) {
            throw new JwtTokenIllegalSubjectException("Token subject is not an integer", e);
        }
    }

    public String getSubject(String token) {
        token = token.replaceFirst("bearer ", "");
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public String generateJwt(User user) {
        Instant now = Instant.now();
        Instant expired = LocalDateTime.now().plus(5, ChronoUnit.DAYS)
                .atZone(ZoneId.systemDefault()).toInstant();

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
