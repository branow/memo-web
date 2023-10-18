package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.VerificationTokenNotFoundException;
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

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken createToken(User user) {
        VerificationToken token = VerificationToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .build();
        System.out.println(token);
        verificationTokenRepository.save(token);
        return token;
    }

    @Override
    public VerificationToken getByToken(String token) {
        return verificationTokenRepository.findVerificationTokenByToken(token)
                .orElseThrow(() -> new VerificationTokenNotFoundException("Verification token with such token not found: " + token));
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
