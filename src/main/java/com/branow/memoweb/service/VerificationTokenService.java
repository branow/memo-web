package com.branow.memoweb.service;

import com.branow.memoweb.model.User;
import com.branow.memoweb.model.VerificationToken;

public interface VerificationTokenService {
    
    VerificationToken createToken(User user);

    VerificationToken getByToken(String verificationToken);

    void delete(VerificationToken verificationToken);

    void deleteByToken(String verificationToken);

}
