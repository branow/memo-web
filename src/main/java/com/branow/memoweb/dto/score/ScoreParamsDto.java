package com.branow.memoweb.dto.score;

import java.time.LocalDateTime;

import com.branow.memoweb.scorecalculator.Score;
import com.branow.memoweb.scorecalculator.ScoreFullParams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreParamsDto implements ScoreFullParams {

    private Score lastScore;
    private LocalDateTime resetTime;
    private int studyRepetition;

}
