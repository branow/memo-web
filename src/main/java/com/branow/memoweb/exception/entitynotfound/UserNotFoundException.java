package com.branow.memoweb.exception.entitynotfound;

public class UserNotFoundException extends EntityNotFoundException{

    public UserNotFoundException(String searchField, Object fieldValue) {
        super("User", searchField, fieldValue);
    }

}
