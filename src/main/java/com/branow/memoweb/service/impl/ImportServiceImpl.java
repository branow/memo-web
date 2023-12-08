package com.branow.memoweb.service.impl;

import com.branow.memoweb.model.Module;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.service.CollectionService;
import com.branow.memoweb.service.FlashcardService;
import com.branow.memoweb.service.ImportService;
import com.branow.memoweb.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImportServiceImpl implements ImportService {

    private final FlashcardService flashcardService;
    private final CollectionService collectionService;
    private final ModuleService moduleService;

    @Override
    public void importFlashcard(Integer flashcardId, Integer targetCollectionId) {
        Flashcard base = flashcardService.getByFlashcardId(flashcardId);
        Flashcard imported = clone(base, targetCollectionId);
        flashcardService.save(imported);
    }

    @Override
    public void importCollection(Integer collectionId, Integer targetModuleId) {
        Collection base  = collectionService.getByCollectionId(collectionId);
        Collection imported = clone(base, targetModuleId);
        collectionService.save(imported);
    }

    @Override
    public void importModule(Integer moduleId, Integer targetUserId) {
        Module base = moduleService.getByModuleId(moduleId);
        Module imported = clone(base, targetUserId);
        moduleService.save(imported);
    }

    private Module clone(Module module, Integer userId) {
        return Module.builder()
                .moduleName(module.getModuleName())
                .description(module.getDescription())
                .accessType(module.getAccessType())
                .collections(module.getCollections().stream().map((c) -> clone(c, null)).toList())
                .user(userId)
                .build();
    }

    private Collection clone(Collection collection, Integer moduleId) {
        return Collection.builder()
                .collectionName(collection.getCollectionName())
                .flashcards(collection.getFlashcards().stream().map(e -> clone(e, null)).toList())
                .module(moduleId)
                .build();
    }

    private Flashcard clone(Flashcard flashcard, Integer collectionId) {
        return Flashcard.builder()
                .frontSide(clone(flashcard.getFrontSide()))
                .backSide(clone(flashcard.getBackSide()))
                .collection(collectionId)
                .build();
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
