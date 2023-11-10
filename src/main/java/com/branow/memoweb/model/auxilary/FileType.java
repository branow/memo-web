package com.branow.memoweb.model.auxilary;

public enum FileType {

    AUDIO(Format.MP3), IMAGE(Format.JPEG, Format.JPG, Format.PNG, Format.WEBP);

    public enum Format {
        JPEG, JPG, PNG, MP3, WEBP;
    }


    private final Format[] formats;

    FileType(Format... formats) {
        this.formats = formats;
    }

    public Format[] getFormats() {
        return formats;
    }

}
