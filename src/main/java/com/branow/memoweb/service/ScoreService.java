package com.branow.memoweb.service;

import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    List<ScoreAggregatedDto> getAggregatedDtoAllByModuleId(Integer moduleId);

    List<ScoreAggregatedDto> getAggregatedDtoAllByCollectionId(Integer collectionId);

}
