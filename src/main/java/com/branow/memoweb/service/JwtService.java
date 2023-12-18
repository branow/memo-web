package com.branow.memoweb.service;

import com.branow.memoweb.model.User;

import java.time.LocalDateTime;

public interface JwtService {

    boolean hasSubject(String token, String subject);

    boolean isExpired(String token);

    LocalDateTime getExpirationDateTime(String token);

    String getSubject(String token);

    String generateJwt(User user);

    String generateVerificationJwt(User user);

}
