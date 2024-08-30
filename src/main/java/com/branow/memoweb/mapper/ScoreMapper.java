package com.branow.memoweb.mapper;

import org.springframework.stereotype.Service;

import com.branow.memoweb.dto.flashcard.FlashcardScoreParamsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.dto.score.ScoreParamsDto;
import com.branow.memoweb.dto.score.ScoreParamsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreSingleDto;
import com.branow.memoweb.dto.studytype.StudyTypeDto;
import com.branow.memoweb.model.Score;
import com.branow.memoweb.model.StudyType;
import com.branow.memoweb.scorecalculator.ScoreFullParams;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScoreMapper {

    private final StudyTypeMapper studyTypeMapper;

    public Score toScore(ScoreParamsDto dto, Integer flashcardId, StudyType studyType) {
        return Score.builder()
                .scoreId(new Score.ScoreId(flashcardId, studyType))
                .resetTime(dto.getResetTime())
                .studyRepetition(dto.getStudyRepetition())
                .studyTime(dto.getLastScore().getTime())
                .score(dto.getLastScore().getScore())
                .build();
    }

    public ScoreAggregatedDto toScoreAggregatedDto(ScoreParamsRepositoryDto repositoryDto, StudyTypeDto studyTypeDto, int agrScore) {
        return ScoreAggregatedDto.builder()
                .studyType(studyTypeDto)
                .resetTime(repositoryDto.getResetTime())
                .score(agrScore)
                .build();
    }

    public ScoreAggregatedDto toScoreAggregatedDto(Score entity, int agrScore) {
        StudyTypeDto studyTypeDto = studyTypeMapper.toStudyTypeDto(entity.getScoreId().getStudyType());
        return ScoreAggregatedDto.builder()
                .studyType(studyTypeDto)
                .resetTime(entity.getResetTime())
                .score(agrScore)
                .build();
    }

    public ScoreParamsDto toScoreParamsDto(FlashcardScoreParamsRepositoryDto dto) {
        return ScoreParamsDto.builder()
                .resetTime(dto.getResetTime())
                .lastScore(toScoreSingleDto(dto))
                .studyRepetition(dto.getStudyRepetition())
                .build();
    }

    public ScoreParamsDto toScoreParamsDto(Score entity) {
        return ScoreParamsDto.builder()
                .resetTime(entity.getResetTime())
                .lastScore(toScoreSingleDto(entity))
                .studyRepetition(entity.getStudyRepetition())
                .build();
    }

    public ScoreParamsDto toScoreParamsDto(ScoreFullParams scoreFullParams) {
        return ScoreParamsDto.builder()
                .resetTime(scoreFullParams.getResetTime())
                .lastScore(scoreFullParams.getLastScore())
                .studyRepetition(scoreFullParams.getStudyRepetition())
                .build();
    }

    public ScoreParamsDto toScoreParamsDto(ScoreParamsRepositoryDto baseDto) {
        return ScoreParamsDto.builder()
                .resetTime(baseDto.getResetTime())
                .lastScore(toScoreSingleDto(baseDto))
                .build();
    }

    public ScoreSingleDto toScoreSingleDto(FlashcardScoreParamsRepositoryDto dto) {
        return ScoreSingleDto.builder()
                .score(dto.getScore())
                .time(dto.getStudyTime())
                .build();
    }

    public ScoreSingleDto toScoreSingleDto(ScoreParamsRepositoryDto baseDto) {
        return ScoreSingleDto.builder()
                .score(baseDto.getScore())
                .time(baseDto.getStudyTime())
                .build();
    }

    public ScoreSingleDto toScoreSingleDto(Score entity) {
        return ScoreSingleDto.builder()
                .score(entity.getScore())
                .time(entity.getStudyTime())
                .build();
    }

}
