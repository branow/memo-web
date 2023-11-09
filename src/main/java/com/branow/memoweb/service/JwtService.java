package com.branow.memoweb.service;

import com.branow.memoweb.model.User;

public interface JwtService {

    boolean hasSubject(String token, String subject);

    String getSubject(String token);

    String generateJwt(User user);

}
