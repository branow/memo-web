package com.branow.memoweb.service;

import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.model.Score;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    List<ScoreAggregatedDto> getAggregatedDtoAllByModuleId(Integer moduleId);

    List<ScoreAggregatedDto> getAggregatedDtoAllByCollectionId(Integer collectionId);

    List<ScoreAggregatedDto> getAggregatedDtoAllByFlashcardId(Integer flashcardId);
}
