package com.branow.memoweb.service;

import com.branow.memoscorecalculator.ScoreParams;
import org.springframework.stereotype.Service;

@Service
public interface ScoreCalculatorService {

    int aggregateScore(ScoreParams scoreParams);

}
