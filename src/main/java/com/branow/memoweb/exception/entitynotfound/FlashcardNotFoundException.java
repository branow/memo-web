package com.branow.memoweb.exception.entitynotfound;

public class FlashcardNotFoundException extends EntityNotFoundException{

    public FlashcardNotFoundException(String searchField, Object fieldValue) {
        super("Flashcard", searchField, fieldValue);
    }

}