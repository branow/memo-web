package com.branow.memoweb.service;

import com.branow.memoscorecalculator.Score;
import com.branow.memoscorecalculator.ScoreParams;
import org.springframework.stereotype.Service;

@Service
public interface ScoreCalculator {

    int calcScore(ScoreParams params);

    ScoreParams calcScoreParams(Score score);

    ScoreParams calcScoreParams(Score score, ScoreParams params);

}
