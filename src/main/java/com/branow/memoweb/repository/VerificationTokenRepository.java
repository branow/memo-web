package com.branow.memoweb.repository;

import com.branow.memoweb.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    Optional<VerificationToken> findVerificationTokenByToken(String token);

}
