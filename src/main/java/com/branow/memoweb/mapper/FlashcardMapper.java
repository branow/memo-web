package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.dto.flashcard.FlashcardShortDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Score;
import com.branow.memoweb.service.FormattedTextService;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlashcardMapper {

    private final ScoreService scoreService;
    private final FormattedTextService formattedTextService;


    public FlashcardSaveDto toFlashcardSaveDto(Flashcard flashcard) {
        return FlashcardSaveDto.builder()
                .flashcardId(flashcard.getFlashcardId())
                .frontSide(flashcard.getFrontSide().getTextId())
                .backSide(flashcard.getBackSide().getTextId())
                .build();
    }

    public Flashcard toFlashcard(FlashcardSaveDto dto, Integer collectionId) {
        FormattedText front = formattedTextService.getByTextId(dto.getFrontSide());
        FormattedText back = formattedTextService.getByTextId(dto.getBackSide());
        List<Score> scores = scoreService.getAllByFlashcardId(dto.getFlashcardId());
        return Flashcard.builder()
                .flashcardId(dto.getFlashcardId())
                .frontSide(front)
                .backSide(back)
                .scores(scores)
                .collection(collectionId)
                .build();
    }

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
