package com.branow.memoweb.exception.entitynotfound;

public class VerificationTokenNotFoundException extends EntityNotFoundException{

    public VerificationTokenNotFoundException(String searchField, Object fieldValue) {
        super("Verification Token", searchField, fieldValue);
    }
}
