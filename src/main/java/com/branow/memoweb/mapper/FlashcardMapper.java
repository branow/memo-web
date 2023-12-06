package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.dto.flashcard.*;
import com.branow.memoweb.dto.formattedtext.FormattedTextDetailsDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Score;
import com.branow.memoweb.service.ScoreCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlashcardMapper {

    private final FormattedTextMapper formattedTextMapper;
    private final ScoreMapper scoreMapper;
    private final ScoreCalculatorService scoreCalculatorService;


    public FlashcardLearnContextDto toFlashcardLearnContextDto(FlashcardShortDetailsRepositoryDto dto,
                                                               FormattedTextDetailsDto front,
                                                               FormattedTextDetailsDto back,
                                                               ScoreAggregatedDto score,
                                                               CollectionShortDetailsRepositoryDto collectionDto,
                                                               ModuleShortDetailsRepositoryDto moduleDto) {
        ModuleShortDetailsDto module = ModuleShortDetailsDto.builder()
                .moduleId(moduleDto.getModuleId())
                .moduleName(moduleDto.getModuleName())
                .access(moduleDto.getAccess())
                .shortDescription(moduleDto.getShortDescription())
                .build();
        CollectionShortDetailsDto collection = CollectionShortDetailsDto.builder()
                .collectionId(collectionDto.getCollectionId())
                .collectionName(collectionDto.getCollectionName())
                .size(collectionDto.getSize())
                .build();
        return FlashcardLearnContextDto.builder()
                .flashcardId(dto.getFlashcardId())
                .score(score)
                .backSide(back)
                .frontSide(front)
                .collection(collection)
                .module(module)
                .build();
    }

    public FlashcardAggregatedScoreDto toFlashcardAggregatedScoreDto(FlashcardScoreParamsRepositoryDto dto) {
        int score = dto.getScore() != null ? scoreCalculatorService.aggregateScore(scoreMapper.toScoreParamsDto(dto)) : 0;
        return FlashcardAggregatedScoreDto.builder()
                .flashcardId(dto.getFlashcardId())
                .score(score)
                .build();
    }

    public Flashcard toFlashcard(FlashcardSaveDto dto, FormattedText front, FormattedText back,
                                 List<Score> scores, Integer collectionId) {
        return Flashcard.builder()
                .flashcardId(dto.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .collection(collectionId)
                .build();
    }

    public FlashcardDetailsDto toFlashcardDetailsDto(FlashcardShortDetailsRepositoryDto dto,
                                                     FormattedTextDetailsDto front,
                                                     FormattedTextDetailsDto back,
                                                     List<ScoreAggregatedDto> scores) {
        return FlashcardDetailsDto.builder()
                .flashcardId(dto.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .build();
    }

    public FlashcardDetailsDto toFlashcardDetailsDto(Flashcard entity, List<ScoreAggregatedDto> scores) {
        FormattedTextDetailsDto front = formattedTextMapper.toFormattedTextDetailsDto(entity.getFrontSide());
        FormattedTextDetailsDto back = formattedTextMapper.toFormattedTextDetailsDto(entity.getBackSide());
        return FlashcardDetailsDto.builder()
                .flashcardId(entity.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .build();
    }

}
