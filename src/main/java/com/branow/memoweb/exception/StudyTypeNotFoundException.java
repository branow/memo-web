package com.branow.memoweb.exception;

public class StudyTypeNotFoundException extends RuntimeException {

    public StudyTypeNotFoundException() {
    }

    public StudyTypeNotFoundException(String message) {
        super(message);
    }

    public StudyTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudyTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public StudyTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
