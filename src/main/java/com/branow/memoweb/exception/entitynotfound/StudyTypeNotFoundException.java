package com.branow.memoweb.exception.entitynotfound;

public class StudyTypeNotFoundException extends EntityNotFoundException {

    public StudyTypeNotFoundException(String searchField, Object fieldValue) {
        super("Study Type", searchField, fieldValue);
    }

}
