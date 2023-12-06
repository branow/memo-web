package com.branow.memoweb.service;

import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.dto.score.ScoreParamsDto;
import com.branow.memoweb.model.Score;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ScoreService {

    List<Score> getAllByFlashcardId(Integer flashcardId);

    List<ScoreAggregatedDto> getAggregatedDtoAllByModuleId(Integer moduleId);

    List<ScoreAggregatedDto> getAggregatedDtoAllByCollectionId(Integer collectionId);

    List<ScoreAggregatedDto> getAggregatedDtoAllByFlashcardId(Integer flashcardId);

    ScoreAggregatedDto getAggregatedDtoByFlashcardIdAndStudyTypeId(Integer flashcardId, Integer studyTypeId);

    ScoreAggregatedDto setScore(Integer flashcardId, Integer studyTypeId, Integer score);

}
