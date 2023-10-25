package com.branow.memoweb.service.score;

import com.branow.memoscorecalculator.Score;

import java.time.LocalDateTime;

public interface ScoreParams {

    Score getLastScore();
    LocalDateTime getResetTime();
    int getStudyRepetition();

}
