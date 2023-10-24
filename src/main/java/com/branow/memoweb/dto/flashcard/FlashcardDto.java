package com.branow.memoweb.dto.flashcard;

import com.branow.memoweb.dto.formattedtext.FormattedTextDto;
import com.branow.memoweb.dto.score.ScoreDto;

import java.util.List;

public class FlashcardDto {

    private Integer flashcardId;
    private FormattedTextDto frontSide;
    private FormattedTextDto backSide;
    private List<ScoreDto> scores;

}
