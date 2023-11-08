package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.module.ModuleDetailsDto;
import com.branow.memoweb.dto.module.ModuleDetailsRepositoryDto;
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
    public ModuleDetailsDto getDetailsDtoByModuleId(Integer id) {
        ModuleDetailsRepositoryDto dto = repository.findDetailsByModuleId(id)
                .orElseThrow(() -> new ModuleNotFoundException("id", id));
        List<CollectionGeneralDetailsDto> collections = collectionService.getGeneralDetailsDtoAllByModuleId(id);
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByModuleId(id);
        return mapper.toModuleDetailsDto(dto, collections, scores);
    }

    @Override
    public ModuleGeneralDetailsDto getGeneralDetailsDtoByModuleId(Integer id) {
        ModuleShortDetailsRepositoryDto dto = repository.findShortDetailsByModuleId(id)
                .orElseThrow(() -> new ModuleNotFoundException("id", id));
        List<CollectionShortDetailsDto> collections = collectionService.getShortDetailsDtoAllByModuleId(id);
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByModuleId(id);
        return mapper.toModuleGeneralDetailsDto(dto, collections, scores);
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
