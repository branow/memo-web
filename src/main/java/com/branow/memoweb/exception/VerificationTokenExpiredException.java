package com.branow.memoweb.exception;

import java.time.LocalDateTime;

public class VerificationTokenExpiredException extends RuntimeException{

    public VerificationTokenExpiredException(LocalDateTime expirationDate) {
        super("Verification Token expired: " + expirationDate);
    }

}
