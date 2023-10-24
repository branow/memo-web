package com.branow.memoweb.dto.score;

import com.branow.memoweb.dto.studytype.StudyTypeDto;

import java.time.LocalDateTime;

public class ScoreSimpleDto {

    private StudyTypeDto studyType;
    private Integer score;
    private LocalDateTime resetTime;

}
