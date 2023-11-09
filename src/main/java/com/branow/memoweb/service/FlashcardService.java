package com.branow.memoweb.service;

import java.util.List;

public interface FlashcardService {
    List<Integer> getFlashcardIdAllByCollectionId(Integer collectionId);
}
