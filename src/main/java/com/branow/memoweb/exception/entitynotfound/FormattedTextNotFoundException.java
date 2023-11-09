package com.branow.memoweb.exception.entitynotfound;

public class FormattedTextNotFoundException extends EntityNotFoundException{

    public FormattedTextNotFoundException(String searchField, Object fieldValue) {
        super("Formatted text", searchField, fieldValue);
    }

}