package com.branow.memoweb.exception;

import static com.brano.print.ShortPrint.form;

public class MediaTooHeavyException extends RuntimeException {

    public MediaTooHeavyException(String mediaName, long maxSize) {
        super(form("Media ")
                .and(mediaName)
                .and(" too heavy ")
                .and(": the max size must be less or equal to ")
                .and(maxSize)
                .and(" bytes")
                .toString());
    }
}
