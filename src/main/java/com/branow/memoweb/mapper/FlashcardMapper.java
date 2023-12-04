package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.dto.flashcard.FlashcardShortDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlashcardMapper {

    private final FormattedTextMapper formattedTextMapper;


    public Flashcard toFlashcard(FlashcardSaveDto dto, FormattedText front, FormattedText back,
                                 List<Score> scores, Integer collectionId) {
        return Flashcard.builder()
                .flashcardId(dto.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .collection(collectionId)
                .build();
    }

    public FlashcardDetailsDto toFlashcardDetailsDto(FlashcardShortDetailsRepositoryDto dto,
                                                     FormattedTextDetailsDto front,
                                                     FormattedTextDetailsDto back,
                                                     List<ScoreAggregatedDto> scores) {
        return FlashcardDetailsDto.builder()
                .flashcardId(dto.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .build();
    }

    public FlashcardDetailsDto toFlashcardDetailsDto(Flashcard entity, List<ScoreAggregatedDto> scores) {
        FormattedTextDetailsDto front = formattedTextMapper.toFormattedTextDetailsDto(entity.getFrontSide());
        FormattedTextDetailsDto back = formattedTextMapper.toFormattedTextDetailsDto(entity.getBackSide());
        return FlashcardDetailsDto.builder()
                .flashcardId(entity.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .build();
    }

}
