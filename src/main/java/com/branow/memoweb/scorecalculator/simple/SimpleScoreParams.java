package com.branow.memoweb.scorecalculator.simple;

import java.time.LocalDateTime;

import com.branow.memoweb.scorecalculator.Score;
import com.branow.memoweb.scorecalculator.ScoreFullParams;

public class SimpleScoreParams implements ScoreFullParams {

    private Score lastScore;
    private LocalDateTime resetTime;
    private int studyRepetition;

    @Override
    public Score getLastScore() {
        return lastScore;
    }

    public void setLastScore(Score lastScore) {
        this.lastScore = lastScore;
    }

    @Override
    public LocalDateTime getResetTime() {
        return resetTime;
    }

    public void setResetTime(LocalDateTime resetTime) {
        this.resetTime = resetTime;
    }

    @Override
    public int getStudyRepetition() {
        return studyRepetition;
    }

    public void setStudyRepetition(int studyRepetition) {
        this.studyRepetition = studyRepetition;
    }

    @Override
    public String toString() {
        return "SimpleScoreParams{" +
                "lastScore=" + lastScore +
                ", resetTime=" + resetTime +
                ", studyRepetition=" + studyRepetition +
                '}';
    }
}
