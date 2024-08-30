package com.branow.memoweb.service;

import org.springframework.stereotype.Service;

import com.branow.memoweb.dto.score.ScoreParamsDto;

@Service
public interface ScoreCalculatorService {

    int aggregateScore(ScoreParamsDto scoreParams);

    ScoreParamsDto scoreParams(int score, ScoreParamsDto scoreFullParams);

    ScoreParamsDto scoreParams(int score);

}
