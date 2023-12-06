package com.branow.memoweb.dto.flashcard;

import java.time.LocalDateTime;

public interface FlashcardScoreParamsRepositoryDto {

    Integer getFlashcardId();
    Integer getStudyType();
    Integer getScore();
    LocalDateTime getStudyTime();
    LocalDateTime getResetTime();
    Integer getStudyRepetition();

}
