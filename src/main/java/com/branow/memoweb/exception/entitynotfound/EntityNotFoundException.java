package com.branow.memoweb.exception.entitynotfound;

import static com.brano.print.ShortPrint.form;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, String searchField, Object fieldValue) {
        super(form()
                .and(entityName).sp()
                .and("with such").sp()
                .and(searchField).sp()
                .and("not found:").sp()
                .and(fieldValue)
                .toString());
    }

}
