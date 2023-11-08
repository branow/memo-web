package com.branow.memoweb.exception;

public class JwtTokenIllegalSubjectException extends RuntimeException {

    public JwtTokenIllegalSubjectException() {
    }

    public JwtTokenIllegalSubjectException(String message) {
        super(message);
    }

    public JwtTokenIllegalSubjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtTokenIllegalSubjectException(Throwable cause) {
        super(cause);
    }

}
