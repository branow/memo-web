package com.branow.memoweb.service.impl;

import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.repository.FlashcardRepository;
import com.branow.memoweb.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlashcardServiceImpl implements FlashcardService {

    private final FlashcardRepository repository;

    @Override
    public List<Integer> getFlashcardIdAllByCollectionId(Integer collectionId) {
        return repository.findFlashcardIdAllByCollectionId(collectionId);
    }

    @Override
    public List<Flashcard> getAllByCollectionId(Integer collectionId) {
        return repository.findAllByCollection(collectionId);
    }
}
