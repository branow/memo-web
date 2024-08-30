package com.branow.memoweb.exception;

public class MediaTooHeavyException extends RuntimeException {

    public MediaTooHeavyException(String mediaName, long maxSize) {
        super(String.format("Media %s too heavy: the max size must be less or equal to %d bytes", mediaName, maxSize));
    }
}
