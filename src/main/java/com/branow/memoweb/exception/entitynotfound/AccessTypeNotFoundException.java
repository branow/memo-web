package com.branow.memoweb.exception.entitynotfound;

public class AccessTypeNotFoundException extends EntityNotFoundException {

    public AccessTypeNotFoundException(String searchField, Object fieldValue) {
        super("Access Type", searchField, fieldValue);
    }

}
