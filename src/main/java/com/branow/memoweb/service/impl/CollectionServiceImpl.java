package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.*;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.dto.user.UserPublicShortDetailsRepositoryDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.CollectionMapper;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.User;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.repository.ModuleRepository;
import com.branow.memoweb.repository.UserRepository;
import com.branow.memoweb.service.CollectionService;
import com.branow.memoweb.service.FlashcardService;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository repository;
    private final CollectionMapper mapper;
    private final ScoreService scoreService;
    private final FlashcardService flashcardService;
    private final JwtBelongingChecker jwtBelongingChecker;
    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;

    @Override
    public Collection getByCollectionId(Integer collectionId) {
        return repository.findById(collectionId)
                .orElseThrow(() -> new EntityNotFoundException(Collection.class, "id", collectionId));
    }

    @Override
    public Collection save(Collection collection) {
        List<Flashcard> flashcards = collection.getFlashcards();
        collection.setFlashcards(List.of());
        repository.save(collection);
        if (flashcards != null) {
            for (Flashcard flashcard: flashcards) {
                flashcard.setCollection(collection.getCollectionId());
                flashcardService.save(flashcard);
            }
        }
        collection.setFlashcards(flashcards);
        return collection;
    }

    @Override
    public List<CollectionShortDetailsDto> getShortDetailsDtoAllByModuleId(Integer moduleId) {
        return repository.findShortDetailsAllByModuleId(moduleId).stream()
                .map(mapper::toCollectionShortDetailsDto).toList();
    }

    @Override
    public List<CollectionGeneralDetailsDto> getGeneralDetailsDtoAllByModuleId(Integer moduleId) {
        return  repository.findShortDetailsAllByModuleId(moduleId).stream()
                .map((e) -> mapper.toCollectionGeneralDetailsDto(e, scoreService.getAggregatedDtoAllByCollectionId(e.getCollectionId())))
                .toList();
    }

    @Override
    public List<Collection> getAllByModuleId(Integer moduleId) {
        return repository.findAllByModule(moduleId);
    }

    @Override
    public CollectionDetailsDto getDetailsDtoByCollectionId(Integer collectionId) {
        CollectionShortDetailsRepositoryDto dto = repository.findShortDetailsByCollectionId(collectionId)
                .orElseThrow(() -> new EntityNotFoundException(Collection.class, "id", collectionId));
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByCollectionId(collectionId);
        List<Integer> flashcardIds = flashcardService.getFlashcardIdAllByCollectionId(collectionId);
        UserPublicShortDetailsRepositoryDto owner = userRepository.findUserPublicShortDetailsByCollectionId(collectionId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "collectionId", collectionId));
        ModuleShortDetailsRepositoryDto module = moduleRepository.findShortDetailsByCollectionId(collectionId)
                .orElseThrow(() -> new EntityNotFoundException(Module.class, "collectionId", collectionId));
        return mapper.toCollectionDetailsDto(dto, scores, flashcardIds, module, owner);
    }

    @Override
    public CollectionSaveDto saveByModuleIdWithJwtCheck(String jwt, Integer moduleId, CollectionSaveDto dto) {
        jwtBelongingChecker.moduleBelongToOrThrow(jwt, moduleId);
        return mapper.toCollectionSaveDto(repository.save(mapper.toCollection(dto, moduleId)));
    }

    @Override
    public void deleteByCollectionIdWithJwtCheck(String jwt, Integer collectionId) {
        jwtBelongingChecker.collectionBelongToOrThrow(jwt, collectionId);
        repository.deleteByCollectionId(collectionId);
    }

}
