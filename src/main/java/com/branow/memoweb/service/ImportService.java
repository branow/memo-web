package com.branow.memoweb.service;

public interface ImportService {

    void importFlashcard(Integer flashcardId, Integer targetCollectionId);

    void importCollection(Integer collectionId, Integer targetModuleId);

}
