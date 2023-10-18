package com.branow.memoweb.exception;

public class AccessTypeNotFoundException extends RuntimeException {

    public AccessTypeNotFoundException() {
    }

    public AccessTypeNotFoundException(String message) {
        super(message);
    }

    public AccessTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public AccessTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
