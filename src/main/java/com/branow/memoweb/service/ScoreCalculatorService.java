package com.branow.memoweb.service;

import com.branow.memoscorecalculator.ScoreFullParams;
import com.branow.memoscorecalculator.ScoreParams;
import com.branow.memoweb.dto.score.ScoreParamsDto;
import org.springframework.stereotype.Service;

@Service
public interface ScoreCalculatorService {

    int aggregateScore(ScoreParamsDto scoreParams);

    ScoreParamsDto scoreParams(int score, ScoreParamsDto scoreFullParams);

    ScoreParamsDto scoreParams(int score);

}
