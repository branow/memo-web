package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.flashcard.FlashcardScoreParamsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.dto.score.ScoreParamsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreParamsDto;
import com.branow.memoweb.dto.score.ScoreSingleDto;
import com.branow.memoweb.dto.studytype.StudyTypeDto;
import com.branow.memoweb.service.ScoreCalculatorService;
import com.branow.memoweb.service.StudyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScoreMapper {

    private final ScoreCalculatorService scoreCalculatorService;
    private final StudyTypeService studyTypeService;

    public ScoreAggregatedDto toScoreAggregatedDto(ScoreParamsRepositoryDto repositoryDto) {
        StudyTypeDto studyTypeDto = studyTypeService.getByStudyName(repositoryDto.getStudyType());
        int aggregate = scoreCalculatorService.aggregateScore(toScoreParamsDto(repositoryDto));
        return ScoreAggregatedDto.builder()
                .studyType(studyTypeDto)
                .resetTime(repositoryDto.getResetTime())
                .score(aggregate)
                .build();
    }

    public ScoreParamsDto toScoreParamsDto(FlashcardScoreParamsRepositoryDto dto) {
        return ScoreParamsDto.builder()
                .resetTime(dto.getResetTime())
                .lastScore(toScoreSingleDto(dto))
                .build();
    }

    public ScoreParamsDto toScoreParamsDto(ScoreParamsRepositoryDto baseDto) {
        return ScoreParamsDto.builder()
                .resetTime(baseDto.getResetTime())
                .lastScore(toScoreSingleDto(baseDto))
                .build();
    }

    public ScoreSingleDto toScoreSingleDto(ScoreParamsRepositoryDto baseDto) {
        return ScoreSingleDto.builder()
                .score(baseDto.getScore())
                .time(baseDto.getStudyTime())
                .build();
    }

    public ScoreSingleDto toScoreSingleDto(FlashcardScoreParamsRepositoryDto dto) {
        return ScoreSingleDto.builder()
                .score(dto.getScore())
                .time(dto.getStudyTime())
                .build();
    }

}
