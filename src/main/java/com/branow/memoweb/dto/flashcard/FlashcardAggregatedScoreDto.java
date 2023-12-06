package com.branow.memoweb.dto.flashcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlashcardAggregatedScoreDto {

    private Integer flashcardId;
    private Integer score;

}
