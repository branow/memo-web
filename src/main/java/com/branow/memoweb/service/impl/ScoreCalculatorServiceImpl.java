package com.branow.memoweb.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.branow.memoweb.dto.score.ScoreParamsDto;
import com.branow.memoweb.dto.score.ScoreSingleDto;
import com.branow.memoweb.mapper.ScoreMapper;
import com.branow.memoweb.scorecalculator.simple.SimpleScoreCalculator;
import com.branow.memoweb.service.ScoreCalculatorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScoreCalculatorServiceImpl implements ScoreCalculatorService {

    private final ScoreMapper mapper;


    @Override
    public int aggregateScore(ScoreParamsDto scoreParams) {
        return new SimpleScoreCalculator().calcScore(scoreParams);
    }

    @Override
    public ScoreParamsDto scoreParams(int score, ScoreParamsDto scoreFullParams) {
        ScoreSingleDto scoreDto = ScoreSingleDto.builder().time(LocalDateTime.now()).score(score).build();
        return mapper.toScoreParamsDto(new SimpleScoreCalculator().calcScoreParams(scoreDto, scoreFullParams));
    }

    @Override
    public ScoreParamsDto scoreParams(int score) {
        ScoreSingleDto scoreDto = ScoreSingleDto.builder().time(LocalDateTime.now()).score(score).build();
        return mapper.toScoreParamsDto(new SimpleScoreCalculator().calcScoreParams(scoreDto));
    }

}
