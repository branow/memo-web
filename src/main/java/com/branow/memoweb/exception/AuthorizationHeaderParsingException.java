package com.branow.memoweb.exception;

public class AuthorizationHeaderParsingException extends RuntimeException {

    public AuthorizationHeaderParsingException() {
        this("", null);
    }

    public AuthorizationHeaderParsingException(String message) {
        this(message, null);
    }

    public AuthorizationHeaderParsingException(String message, Throwable cause) {
        super("Illegal Authorization header: " + message, cause);
    }

    public AuthorizationHeaderParsingException(Throwable cause) {
        super(cause);
    }

}
