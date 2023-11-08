package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.mapper.ScoreMapper;
import com.branow.memoweb.repository.ScoreRepository;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository repository;
    private final ScoreMapper mapper;


    @Override
    public List<ScoreAggregatedDto> getAggregatedDtoAllByModuleId(Integer moduleId) {
        return repository.findAverageParamsAllByModuleId(moduleId).stream()
                .map(mapper::toScoreAggregatedDto).toList();
    }

    @Override
    public List<ScoreAggregatedDto> getAggregatedDtoAllByCollectionId(Integer collectionId) {
        return repository.findAverageParamsAllByCollectionId(collectionId).stream()
                .map(mapper::toScoreAggregatedDto).toList();
    }

}
