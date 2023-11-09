package com.branow.memoweb.dto.flashcard;

import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlashcardDetailsDto {

    private Integer flashcardId;
    private FormattedTextGeneralDetailsDto frontSide;
    private FormattedTextGeneralDetailsDto backSide;
    private List<ScoreAggregatedDto> scores;

}
