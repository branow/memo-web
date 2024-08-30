package com.branow.memoweb.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, String searchField, Object fieldValue) {
        super(String.format("%s with such %s not found: %s",
                entityClass.getSimpleName(),
                searchField,
                fieldValue.toString()));
    }

}
