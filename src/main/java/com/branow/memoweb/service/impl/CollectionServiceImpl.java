package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.*;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.exception.entitynotfound.CollectionNotFoundException;
import com.branow.memoweb.mapper.CollectionMapper;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.service.*;
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
        return repository.findAllByModule(moduleId);
    }

    @Override
    public CollectionDetailsDto getDetailsDtoByCollectionId(Integer collectionId) {
        CollectionShortDetailsRepositoryDto dto = repository.findCollectionShortDetailsDtoByCollectionId(collectionId)
                .orElseThrow(() -> new CollectionNotFoundException("id", collectionId));
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByCollectionId(collectionId);
        List<Integer> flashcardIds = flashcardService.getFlashcardIdAllByCollectionId(collectionId);
        return mapper.toCollectionDetailsDto(dto, scores, flashcardIds);
    }

    @Override
    public CollectionSaveDto saveByModuleIdWithJwtCheck(String jwt, Integer moduleId, CollectionSaveDto dto) {
        jwtBelongingChecker.moduleBelongToOrThrow(jwt, moduleId);
        return mapper.toCollectionSaveDto(repository.save(mapper.toCollection(dto, moduleId)));
    }

    @Override
    public void deleteByCollectionIdWithJwtCheck(String jwt, Integer collectionId) {
        jwtBelongingChecker.collectionBelongToOrThrow(jwt, collectionId);
        repository.deleteById(collectionId);
    }

}
