package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.module.ModuleGeneralDetailsDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.exception.entitynotfound.ModuleNotFoundException;
import com.branow.memoweb.mapper.ModuleMapper;
import com.branow.memoweb.repository.ModuleRepository;
import com.branow.memoweb.service.CollectionService;
import com.branow.memoweb.service.ModuleService;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository repository;
    private final ModuleMapper mapper;
    private final CollectionService collectionService;
    private final ScoreService scoreService;


    @Override
    public ModuleGeneralDetailsDto getSimpleDtoById(Integer id) {
        ModuleShortDetailsRepositoryDto dto = repository.findModuleShortDetailsDtoById(id)
                .orElseThrow(() -> new ModuleNotFoundException("module id", id));
        List<CollectionShortDetailsDto> collections = collectionService.getShortDetailsDtoAllByModuleId(id);
        List<ScoreAggregatedDto> scores = scoreService.getSimpleDtoAllByModuleId(id);
        return mapper.toModuleSimpleDto(dto, collections, scores);
    }

    @Override
    public List<Integer> getIdAllByUserId(Integer userId) {
        return repository.findModuleIdAllByUserId(userId);
    }

    @Override
    public List<Integer> getIdWithPublicAccessAllByUserId(Integer userId) {
        return repository.findModuleIdWithPublicAccessAllByUserId(userId);
    }

}
