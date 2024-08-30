package com.branow.memoweb.scorecalculator;

public interface ScoreCalculator {


    int calcScore(ScoreParams params);

    ScoreFullParams calcScoreParams(Score score);

    ScoreFullParams calcScoreParams(Score score, ScoreFullParams params);

}
