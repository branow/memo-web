package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.dto.score.ScoreParamsDto;
import com.branow.memoweb.dto.studytype.StudyTypeDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.ScoreMapper;
import com.branow.memoweb.model.Score;
import com.branow.memoweb.model.StudyType;
import com.branow.memoweb.repository.ScoreRepository;
import com.branow.memoweb.service.ScoreCalculatorService;
import com.branow.memoweb.service.ScoreService;
import com.branow.memoweb.service.StudyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository repository;
    private final ScoreMapper mapper;
    private final ScoreCalculatorService scoreCalculatorService;
    private final StudyTypeService studyTypeService;

    @Override
    public List<Score> getAllByFlashcardId(Integer flashcardId) {
        return repository.findAllByFlashcard(flashcardId);
    }

    @Override
    public List<ScoreAggregatedDto> getAggregatedDtoAllByModuleId(Integer moduleId) {
        return repository.findAverageParamsAllByModuleId(moduleId).stream()
                .map((param) -> {
                    StudyTypeDto studyTypeDto = studyTypeService.getDtoByStudyName(param.getStudyType());
                    int agrScore = scoreCalculatorService.aggregateScore(mapper.toScoreParamsDto(param));
                    return mapper.toScoreAggregatedDto(param, studyTypeDto, agrScore);
                }).toList();
    }

    @Override
    public List<ScoreAggregatedDto> getAggregatedDtoAllByCollectionId(Integer collectionId) {
        return repository.findAverageParamsAllByCollectionId(collectionId).stream()
                .map(param -> {
                    StudyTypeDto studyTypeDto = studyTypeService.getDtoByStudyName(param.getStudyType());
                    int agrScore = scoreCalculatorService.aggregateScore(mapper.toScoreParamsDto(param));
                    return mapper.toScoreAggregatedDto(param, studyTypeDto, agrScore);
                }).toList();
    }

    @Override
    public List<ScoreAggregatedDto> getAggregatedDtoAllByFlashcardId(Integer flashcardId) {
        return repository.findParamsAllByFlashcardId(flashcardId).stream()
                .map(param -> {
                    StudyTypeDto studyTypeDto = studyTypeService.getDtoByStudyName(param.getStudyType());
                    int agrScore = scoreCalculatorService.aggregateScore(mapper.toScoreParamsDto(param));
                    return mapper.toScoreAggregatedDto(param, studyTypeDto, agrScore);
                }).toList();
    }

    @Override
    public ScoreAggregatedDto getAggregatedDtoByFlashcardIdAndStudyTypeId(Integer flashcardId, Integer studyTypeId) {
        Score score = repository.findByFlashcardAndStudyType(flashcardId, studyTypeId)
                .orElse(getEmptyScore(flashcardId, studyTypeId));
        int agrScore = scoreCalculatorService.aggregateScore(mapper.toScoreParamsDto(score));
        return mapper.toScoreAggregatedDto(score, agrScore);
    }

    @Override
    public ScoreAggregatedDto setScore(Integer flashcardId, Integer studyTypeId, Integer score) {
        Optional<Score> opt = repository.findByFlashcardAndStudyType(flashcardId, studyTypeId);
        ScoreParamsDto paramsDto = opt.isPresent() ?
                scoreCalculatorService.scoreParams(score, mapper.toScoreParamsDto(opt.get())) :
                scoreCalculatorService.scoreParams(score);
        StudyType studyType = studyTypeService.getByStudyId(studyTypeId);
        Score entity = repository.save(mapper.toScore(paramsDto, flashcardId, studyType));
        int agr = scoreCalculatorService.aggregateScore(mapper.toScoreParamsDto(entity));
        return mapper.toScoreAggregatedDto(entity, agr);
    }

    private Score getEmptyScore(Integer flashcardId, Integer studyTypeId) {
        return Score.builder()
                .scoreId(new Score.ScoreId(
                        flashcardId,
                        studyTypeService.getByStudyId(studyTypeId)
                ))
                .score(0)
                .studyTime(LocalDateTime.now())
                .resetTime(LocalDateTime.now())
                .studyRepetition(0)
                .build();
    }

}
