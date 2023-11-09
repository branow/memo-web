package com.branow.memoweb.dto.flashcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlashcardSaveDto {

    private Integer flashcardId;
    private Integer frontSide;
    private Integer backSide;

}
