package com.branow.memoweb.service;

import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.dto.flashcard.FlashcardLearnContextDto;
import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.model.Flashcard;

import java.util.List;

public interface FlashcardService {

    Flashcard getByFlashcardId(Integer flashcardId);

    Flashcard save(Flashcard flashcard);

    FlashcardLearnContextDto getLearnContextDtoByFlashcardIdAndStudyTypeId(Integer flashcardId, Integer studyTypeId);

    List<Integer> getFlashcardIdAllByCollectionId(Integer collectionId);

    List<Flashcard> getAllByCollectionId(Integer collectionId);

    FlashcardDetailsDto getDetailsDtoByFlashcardId(Integer flashcardId);

    FlashcardDetailsDto saveByCollectionIdWithJwtCheck(String jwt, Integer collectionId, FlashcardSaveDto dto);

    void deleteByFlashcardIdWithJwtCheck(String jwt, Integer flashcardId);
}
