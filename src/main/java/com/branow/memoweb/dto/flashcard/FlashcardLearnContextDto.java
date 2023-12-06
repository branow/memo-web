package com.branow.memoweb.dto.flashcard;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextDetailsDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlashcardLearnContextDto {

    private Integer flashcardId;
    private FormattedTextDetailsDto frontSide;
    private FormattedTextDetailsDto backSide;
    private ScoreAggregatedDto score;
    private CollectionShortDetailsDto collection;
    private ModuleShortDetailsDto module;

}
