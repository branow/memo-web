package com.branow.memoweb.exception.entitynotfound;

public class CollectionNotFoundException extends EntityNotFoundException{

    public CollectionNotFoundException(String searchField, Object fieldValue) {
        super("Collection", searchField, fieldValue);
    }

}
