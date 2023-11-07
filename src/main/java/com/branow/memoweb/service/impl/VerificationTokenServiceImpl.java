package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.entitynotfound.VerificationTokenNotFoundException;
import com.branow.memoweb.model.User;
import com.branow.memoweb.model.VerificationToken;
import com.branow.memoweb.repository.VerificationTokenRepository;
import com.branow.memoweb.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository repository;

    @Override
    public VerificationToken createToken(User user) {
        VerificationToken token = VerificationToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .build();
        repository.save(token);
        return token;
    }

    @Override
    public VerificationToken getByToken(String token) {
        return repository.findVerificationTokenByToken(token)
                .orElseThrow(() -> new VerificationTokenNotFoundException("token", token));
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        repository.delete(verificationToken);
    }

    @Override
    public void deleteByToken(String verificationToken) {
        repository.deleteByToken(verificationToken);
    }
}
