package com.branow.memoweb.service.impl;

import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.service.FlashcardService;
import com.branow.memoweb.service.FormattedTextService;
import com.branow.memoweb.service.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImportServiceImpl implements ImportService {

    private final FlashcardService flashcardService;


    @Override
    public void importFlashcard(Integer flashcardId, Integer targetCollectionId) {
        Flashcard base = flashcardService.getByFlashcardId(flashcardId);
        Flashcard imported = Flashcard.builder()
                .frontSide(clone(base.getFrontSide()))
                .backSide(clone(base.getBackSide()))
                .collection(targetCollectionId)
                .build();
        flashcardService.save(imported);
    }

    private FormattedText clone(FormattedText formattedText) {
        return FormattedText.builder()
                .text(formattedText.getText())
                .format(formattedText.getFormat())
                .image(formattedText.getImage())
                .audio(formattedText.getAudio())
                .build();
    }

}
