package com.branow.memoweb.exception;

public class JwtIllegalSubjectException extends RuntimeException {

    public JwtIllegalSubjectException() {
    }

    public JwtIllegalSubjectException(String message) {
        super(message);
    }

    public JwtIllegalSubjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtIllegalSubjectException(Throwable cause) {
        super(cause);
    }

}
