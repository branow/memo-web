package com.branow.memoweb.exception;

import static com.brano.print.ShortPrint.form;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entityClass, String searchField, Object fieldValue) {
        super(form()
                .and(entityClass.getSimpleName()).sp()
                .and("with such").sp()
                .and(searchField).sp()
                .and("not found:").sp()
                .and(fieldValue)
                .toString());
    }

}
