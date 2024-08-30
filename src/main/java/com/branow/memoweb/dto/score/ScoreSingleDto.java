package com.branow.memoweb.dto.score;

import java.time.LocalDateTime;

import com.branow.memoweb.scorecalculator.Score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreSingleDto implements Score {

    private int score;
    private LocalDateTime time;

}
