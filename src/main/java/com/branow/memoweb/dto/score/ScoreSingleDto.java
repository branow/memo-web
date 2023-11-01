package com.branow.memoweb.dto.score;

import com.branow.memoscorecalculator.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreSingleDto implements Score {

    private int score;
    private LocalDateTime time;

}
