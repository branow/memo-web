package com.branow.memoweb.dto.flashcard;

import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
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
    private FormattedTextSaveDto frontSide;
    private FormattedTextSaveDto backSide;

}
