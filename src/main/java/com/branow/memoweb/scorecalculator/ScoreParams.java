package com.branow.memoweb.scorecalculator;

import java.time.LocalDateTime;

public interface ScoreParams {

    Score getLastScore();
    LocalDateTime getResetTime();

}
