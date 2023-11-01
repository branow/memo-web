package com.branow.memoweb.dto.score;

import com.branow.memoscorecalculator.Score;
import com.branow.memoscorecalculator.ScoreFullParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreParamsDto implements ScoreFullParams {

    private Score lastScore;
    private LocalDateTime resetTime;
    private int studyRepetition;

}
