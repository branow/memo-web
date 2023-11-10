package com.branow.memoweb.exception;

import com.branow.memoweb.model.auxilary.FileType;

public class FileTypeNotFound extends RuntimeException {

    public FileTypeNotFound(FileType.Format format) {
        super("FileType for such format not found: " + format);
    }


}
