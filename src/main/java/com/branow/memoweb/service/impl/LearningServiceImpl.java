package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.flashcard.FlashcardAggregatedScoreDto;
import com.branow.memoweb.dto.flashcard.FlashcardScoreParamsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.mapper.FlashcardMapper;
import com.branow.memoweb.repository.FlashcardRepository;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.LearningService;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class LearningServiceImpl implements LearningService {

    private final JwtBelongingChecker jwtBelongingChecker;
    private final FlashcardRepository flashcardRepository;
    private final FlashcardMapper flashcardMapper;
    private final ScoreService scoreService;

    @Override
    public List<Integer> getFlashcardIdsToLearnWithJwtCheck(String jwt, Integer studyType, List<Integer> collections,
                                                List<Integer> levels, Boolean sort) {
        List<Scope> scopes = levels.stream().map(this::getScopeByLevel).toList();
        List<FlashcardScoreParamsRepositoryDto> dtos = new ArrayList<>();
        collections.forEach(collection -> dtos.addAll(getFlashcardScoreAllByCollectionIdAndStudyTypeIdWithJwtCheck(jwt, collection, studyType)));
        Stream<FlashcardAggregatedScoreDto> agr = dtos.stream().map(flashcardMapper::toFlashcardAggregatedScoreDto);
        agr = agr.filter((dto) -> scopes.stream().anyMatch((scope) -> scope.inScope(dto.getScore())));
        if (sort) {
            agr = agr.sorted(Comparator.comparing(FlashcardAggregatedScoreDto::getScore));
        }
        return agr.map(FlashcardAggregatedScoreDto::getFlashcardId).toList();
    }

    @Override
    public ScoreAggregatedDto setScoreToFlashcardWithJwtCheck(String jwt, Integer flashcardId, Integer studyTypeId, Integer score) {
        jwtBelongingChecker.flashcardBelongToOrThrow(jwt, flashcardId);
        return scoreService.setScore(flashcardId, studyTypeId, score);
    }

    private List<FlashcardScoreParamsRepositoryDto> getFlashcardScoreAllByCollectionIdAndStudyTypeIdWithJwtCheck(
            String jwt, Integer collection, Integer studyType) {
        jwtBelongingChecker.collectionBelongToOrThrow(jwt, collection);
        return flashcardRepository.findFlashcardScoreAllByCollectionIdAndStudyTypeId(collection, studyType);
    }


    private Scope getScopeByLevel(int level) {
        return switch (level) {
            case 0 -> new Scope(0, 49);
            case 1 -> new Scope(50, 74);
            case 2 -> new Scope(75, 94);
            case 3 -> new Scope(95, 100);
            default -> throw new IllegalArgumentException("Not found scope for such level: " + level);
        };
    }


    private static class Scope {

        private final int min;
        private final int max;

        public Scope(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public boolean inScope(int value) {
            return value >= min && value <= max;
        }
    }

}
