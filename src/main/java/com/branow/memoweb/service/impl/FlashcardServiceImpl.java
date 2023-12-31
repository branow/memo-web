package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.dto.flashcard.FlashcardDetailsDto;
import com.branow.memoweb.dto.flashcard.FlashcardLearnContextDto;
import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.dto.flashcard.FlashcardShortDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextDetailsDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.FlashcardMapper;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Module;
import com.branow.memoweb.model.Score;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.repository.FlashcardRepository;
import com.branow.memoweb.repository.ModuleRepository;
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
    private final CollectionRepository collectionRepository;
    private final ModuleRepository moduleRepository;


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
                .orElseThrow(() -> new EntityNotFoundException(Flashcard.class, "id", flashcardId));
        FormattedTextDetailsDto front = formattedTextService.getDetailsDtoByTextId(dto.getFrontSide());
        FormattedTextDetailsDto back = formattedTextService.getDetailsDtoByTextId(dto.getBackSide());
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByFlashcardId(flashcardId);
        return mapper.toFlashcardDetailsDto(dto, front, back, scores);
    }

    @Override
    public Flashcard getByFlashcardId(Integer flashcardId) {
        return repository.findById(flashcardId)
                .orElseThrow(() -> new EntityNotFoundException(Flashcard.class, "id", flashcardId));
    }

    @Override
    public Flashcard save(Flashcard flashcard) {
        return repository.save(flashcard);
    }

    @Override
    public FlashcardLearnContextDto getLearnContextDtoByFlashcardIdAndStudyTypeId(Integer flashcardId, Integer studyTypeId) {
        FlashcardShortDetailsRepositoryDto dto = repository.findShortDetailsByFlashcardId(flashcardId)
                .orElseThrow(() -> new EntityNotFoundException(Flashcard.class, "id", flashcardId));
        FormattedTextDetailsDto front = formattedTextService.getDetailsDtoByTextId(dto.getFrontSide());
        FormattedTextDetailsDto back = formattedTextService.getDetailsDtoByTextId(dto.getBackSide());
        ScoreAggregatedDto score = scoreService.getAggregatedDtoByFlashcardIdAndStudyTypeId(flashcardId, studyTypeId);
        CollectionShortDetailsRepositoryDto collection = collectionRepository
                .findShortDetailsByFlashcardId(flashcardId)
                .orElseThrow(() -> new EntityNotFoundException(Collection.class, "flashcard id", flashcardId));
        ModuleShortDetailsRepositoryDto module = moduleRepository
                .findShortDetailsByFlashcardId(flashcardId)
                .orElseThrow(() -> new EntityNotFoundException(Module.class, "flashcard id", flashcardId));
        return mapper.toFlashcardLearnContextDto(dto, front, back, score, collection, module);
    }

    @Override
    public FlashcardDetailsDto saveByCollectionIdWithJwtCheck(String jwt, Integer collectionId, FlashcardSaveDto dto) {
        jwtBelongingChecker.collectionBelongToOrThrow(jwt, collectionId);
        if (dto.getFlashcardId() == null) {
            Flashcard entity = Flashcard.builder()
                    .collection(collectionId)
                    .frontSide(FormattedText.builder().text("").build())
                    .backSide(FormattedText.builder().text("").build())
                    .build();
            return mapper.toFlashcardDetailsDto(repository.save(entity), List.of());
        }
        FormattedText front = formattedTextService.save(dto.getFrontSide());
        FormattedText back = formattedTextService.save(dto.getBackSide());
        List<Score> scores = scoreService.getAllByFlashcardId(dto.getFlashcardId());
        Flashcard flashcard =  mapper.toFlashcard(dto, front, back, scores, collectionId);
        List<ScoreAggregatedDto> scoreAggregated = scoreService.getAggregatedDtoAllByFlashcardId(flashcard.getFlashcardId());
        return mapper.toFlashcardDetailsDto(flashcard, scoreAggregated);
    }

    @Override
    public void deleteByFlashcardIdWithJwtCheck(String jwt, Integer flashcardId) {
        jwtBelongingChecker.flashcardBelongToOrThrow(jwt, flashcardId);
        repository.deleteByFlashcardId(flashcardId);
    }
}
