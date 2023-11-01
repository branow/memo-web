package com.branow.memoweb.dto.score;

import java.time.LocalDateTime;

public interface ScoreParamsRepositoryDto {

    String getStudyType();
    Integer getScore();
    LocalDateTime getStudyTime();
    LocalDateTime getResetTime();

}
