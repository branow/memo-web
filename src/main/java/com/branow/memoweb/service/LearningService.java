package com.branow.memoweb.service;

import com.branow.memoweb.dto.score.ScoreAggregatedDto;

import java.util.List;

public interface LearningService {

    List<Integer> getFlashcardIdsToLearnWithJwtCheck(String jwt, Integer studyType, List<Integer> collections,
                                         List<Integer> levels, Boolean sort);

    ScoreAggregatedDto setScoreToFlashcardWithJwtCheck(String jwt, Integer flashcardId, Integer studyTypeId, Integer score);
}
