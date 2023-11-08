package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.mapper.CollectionMapper;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.service.CollectionService;
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

}
