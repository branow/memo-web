package com.branow.memoweb.service;

import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.model.Flashcard;

import java.util.List;

public interface FlashcardService {
    List<Integer> getFlashcardIdAllByCollectionId(Integer collectionId);

    List<Flashcard> getAllByCollectionId(Integer collectionId);

    FlashcardDetailsDto getDetailsDtoByFlashcardId(Integer flashcardId);
}
