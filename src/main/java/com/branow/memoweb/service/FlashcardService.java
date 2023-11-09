package com.branow.memoweb.service;

import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.model.Flashcard;

import java.util.List;

public interface FlashcardService {
    List<Integer> getFlashcardIdAllByCollectionId(Integer collectionId);

    List<Flashcard> getAllByCollectionId(Integer collectionId);

    FlashcardDetailsDto getDetailsDtoByFlashcardId(Integer flashcardId);

    FlashcardSaveDto saveByCollectionIdWithJwtCheck(String jwt, Integer collectionId, FlashcardSaveDto dto);

    void deleteByFlashcardIdWithJwtCheck(String jwt, Integer flashcardId);
}
