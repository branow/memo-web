package com.branow.memoweb.exception;

import com.branow.memoweb.model.VerificationToken;

public class VerificationTokenExpiredException extends RuntimeException{

    public VerificationTokenExpiredException(VerificationToken token) {
        super("Verification Token expired: " + token);
    }

}
