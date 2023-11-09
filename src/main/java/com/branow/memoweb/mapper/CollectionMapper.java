package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.*;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollectionMapper {

    private final FlashcardService flashcardService;


    public CollectionSaveDto toCollectionSaveDto(Collection collection) {
        return CollectionSaveDto.builder()
                .collectionId(collection.getCollectionId())
                .collectionName(collection.getCollectionName())
                .build();
    }

    public Collection toCollection(CollectionSaveDto dto, Integer moduleId) {
        List<Flashcard> flashcards = flashcardService.getAllByCollectionId(dto.getCollectionId());
        return Collection.builder()
                .collectionId(dto.getCollectionId())
                .collectionName(dto.getCollectionName())
                .flashCards(flashcards)
                .module(moduleId)
                .build();
    }

    public CollectionDetailsDto toCollectionDetailsDto(CollectionShortDetailsRepositoryDto dto,
                                                       List<ScoreAggregatedDto> scores,
                                                       List<Integer> flashcardIds) {
        return CollectionDetailsDto.builder()
                .collectionId(dto.getCollectionId())
                .collectionName(dto.getCollectionName())
                .size(dto.getSize())
                .flashcardIds(flashcardIds)
                .scores(scores)
                .build();
    }

    public CollectionGeneralDetailsDto toCollectionGeneralDetailsDto(CollectionShortDetailsRepositoryDto dto,
                                                                     List<ScoreAggregatedDto> scores) {
        return CollectionGeneralDetailsDto.builder()
                .collectionId(dto.getCollectionId())
                .collectionName(dto.getCollectionName())
                .size(dto.getSize())
                .scores(scores)
                .build();
    }

    public CollectionShortDetailsDto toCollectionShortDetailsDto(CollectionShortDetailsRepositoryDto repositoryDto) {
        return CollectionShortDetailsDto.builder()
                .collectionId(repositoryDto.getCollectionId())
                .collectionName(repositoryDto.getCollectionName())
                .size(repositoryDto.getSize())
                .build();
    }

}
