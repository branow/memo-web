package com.branow.memoweb.service.score.impl;

import com.branow.memoscorecalculator.simple.SimpleScore;

import java.time.LocalDateTime;

public class ScoreImpl extends SimpleScore {
    public ScoreImpl(int score, LocalDateTime time) {
        super(score, time);
    }
}
