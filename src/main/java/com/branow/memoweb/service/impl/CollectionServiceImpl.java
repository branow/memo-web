package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionDetailsDto;
import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.exception.entitynotfound.CollectionNotFoundException;
import com.branow.memoweb.mapper.CollectionMapper;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.service.CollectionService;
import com.branow.memoweb.service.FlashcardService;
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

    @Override
    public List<CollectionShortDetailsDto> getShortDetailsDtoAllByModuleId(Integer moduleId) {
        return repository.findCollectionShortDetailsDtoAllByModuleId(moduleId).stream()
                .map(mapper::toCollectionShortDetailsDto).toList();
    }

    @Override
    public List<CollectionGeneralDetailsDto> getGeneralDetailsDtoAllByModuleId(Integer moduleId) {
        return  repository.findCollectionShortDetailsDtoAllByModuleId(moduleId).stream()
                .map((e) -> mapper.toCollectionGeneralDetailsDto(e, scoreService.getAggregatedDtoAllByCollectionId(e.getCollectionId())))
                .toList();
    }

    @Override
    public List<Collection> getAllByModuleId(Integer moduleId) {
        return repository.findAllByModuleId(moduleId);
    }

    @Override
    public CollectionDetailsDto getDetailsDtoByCollectionId(Integer collectionId) {
        CollectionShortDetailsRepositoryDto dto = repository.findCollectionShortDetailsDtoByCollectionId(collectionId)
                .orElseThrow(() -> new CollectionNotFoundException("id", collectionId));
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByCollectionId(collectionId);
        List<Integer> flashcardIds = flashcardService.getFlashcardIdAllByCollectionId(collectionId);
        return mapper.toCollectionDetailsDto(dto, scores, flashcardIds);
    }

}
