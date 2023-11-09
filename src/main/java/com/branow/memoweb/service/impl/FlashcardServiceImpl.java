package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.dto.flashcard.FlashcardShortDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.exception.entitynotfound.FlashcardNotFoundException;
import com.branow.memoweb.mapper.FlashcardMapper;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.repository.FlashcardRepository;
import com.branow.memoweb.service.FlashcardService;
import com.branow.memoweb.service.FormattedTextService;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FlashcardServiceImpl implements FlashcardService {

    private final FlashcardRepository repository;
    private final FlashcardMapper mapper;
    private final ScoreService scoreService;
    private final FormattedTextService formattedTextService;
    private final JwtBelongingChecker jwtBelongingChecker;


    @Override
    public List<Integer> getFlashcardIdAllByCollectionId(Integer collectionId) {
        return repository.findFlashcardIdAllByCollectionId(collectionId);
    }

    @Override
    public List<Flashcard> getAllByCollectionId(Integer collectionId) {
        return repository.findAllByCollection(collectionId);
    }

    @Override
    public FlashcardDetailsDto getDetailsDtoByFlashcardId(Integer flashcardId) {
        FlashcardShortDetailsRepositoryDto dto = repository.findShortDetailsByFlashcardId(flashcardId)
                .orElseThrow(() -> new FlashcardNotFoundException("id", flashcardId));
        FormattedTextGeneralDetailsDto front = formattedTextService.getGeneralDetailsDtoByTextId(dto.getFrontSide());
        FormattedTextGeneralDetailsDto back = formattedTextService.getGeneralDetailsDtoByTextId(dto.getBackSide());
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByFlashcardId(flashcardId);
        return mapper.toFlashcardDetailsDto(dto, front, back, scores);
    }

    @Override
    public FlashcardSaveDto saveByCollectionIdWithJwtCheck(String jwt, Integer collectionId, FlashcardSaveDto dto) {
        jwtBelongingChecker.collectionBelongToOrThrow(jwt, collectionId);
        if (dto.getBackSide() == null) {
            dto.setBackSide(formattedTextService.createEmpty().getTextId());
        }
        if (dto.getFrontSide() == null) {
            dto.setFrontSide(formattedTextService.createEmpty().getTextId());
        }
        return mapper.toFlashcardSaveDto(repository.save(mapper.toFlashcard(dto, collectionId)));
    }

    @Override
    public void deleteByFlashcardIdWithJwtCheck(String jwt, Integer flashcardId) {
        jwtBelongingChecker.flashcardBelongToOrThrow(jwt, flashcardId);
        repository.deleteById(flashcardId);
    }
}
