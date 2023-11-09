package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.dto.flashcard.FlashcardShortDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlashcardMapper {


    public FlashcardDetailsDto toFlashcardDetailsDto(FlashcardShortDetailsRepositoryDto dto,
                                                     FormattedTextGeneralDetailsDto front,
                                                     FormattedTextGeneralDetailsDto back,
                                                     List<ScoreAggregatedDto> scores) {
        return FlashcardDetailsDto.builder()
                .flashcardId(dto.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .build();
    }

}
