package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionShortDto;
import com.branow.memoweb.dto.module.ModuleShortDto;
import com.branow.memoweb.dto.module.ModuleSimpleDto;
import com.branow.memoweb.dto.score.ScoreSimpleDto;
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
    public ModuleSimpleDto getSimpleDtoById(Integer id) {
        ModuleShortDto moduleShortDto = repository.findModuleShortDtoById(id);
        List<CollectionShortDto> collections = collectionService.getCollectionShortDtoAllByModuleId(id);
        List<ScoreSimpleDto> scores = scoreService.getScoreSimpleDtoAllByModuleId(id);
        return mapper.toModuleSimpleDto(moduleShortDto, collections, scores);
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
