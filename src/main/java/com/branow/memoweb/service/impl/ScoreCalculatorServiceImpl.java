package com.branow.memoweb.service.impl;

import com.branow.memoscorecalculator.ScoreCalculator;
import com.branow.memoscorecalculator.ScoreParams;
import com.branow.memoscorecalculator.simple.SimpleScoreCalculator;
import com.branow.memoweb.service.ScoreCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class ScoreCalculatorServiceImpl implements ScoreCalculatorService {

    private final ScoreCalculator scoreCalculator = new SimpleScoreCalculator();


    @Override
    public int aggregateScore(ScoreParams scoreParams) {
        return scoreCalculator.calcScore(scoreParams);
    }

}
