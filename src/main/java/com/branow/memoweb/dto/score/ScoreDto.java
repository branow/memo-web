package com.branow.memoweb.dto.score;

import com.branow.memoweb.dto.studytype.StudyTypeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreDto {

    private Integer flashcard;
    private StudyTypeDto studyType;
    private Integer score;
    private LocalDateTime studyTime;
    private LocalDateTime resetTime;
    private Integer studyRepetition;

}
