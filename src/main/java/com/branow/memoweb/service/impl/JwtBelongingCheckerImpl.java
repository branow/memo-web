package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.exception.JwtIllegalSubjectException;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Module;
import com.branow.memoweb.repository.*;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtBelongingCheckerImpl implements JwtBelongingChecker {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    @Override
    public void belongToOrThrow(String jwt, Integer userId) {
        if (!belongTo(jwt, userId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given user id");
        }
    }

    @Override
    public void moduleBelongToOrThrow(String jwt, Integer moduleId) {
        if (!moduleBelongTo(jwt, moduleId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given module id");
        }
    }

    @Override
    public void collectionBelongToOrThrow(String jwt, Integer collectionId) {
        if (!collectionBelongTo(jwt, collectionId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given collection id");
        }
    }

    @Override
    public void flashcardBelongToOrThrow(String jwt, Integer flashcardId) {
        if (!flashcardBelongTo(jwt, flashcardId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given flashcard id");
        }
    }

    @Override
    public void formattedTextBelongToOrThrow(String jwt, Integer textId) {
        if (!formattedTextBelongTo(jwt, textId)) {
            throw new JwtIllegalSubjectException("Jwt subject is not matching the given text id");
        }
    }

    @Override
    public boolean belongTo(String jwt, Integer userId) {
        return jwtService.hasSubject(jwt, userId.toString());
    }

    @Override
    public boolean moduleBelongTo(String jwt, Integer moduleId) {
        return belongTo(jwt, userRepository.findUserIdByModuleId(moduleId)
                .orElseThrow(() -> new EntityNotFoundException(Module.class, "id", moduleId)));
    }

    @Override
    public boolean collectionBelongTo(String jwt, Integer collectionId) {
        return belongTo(jwt, userRepository.findUserIdByCollectionId(collectionId)
                .orElseThrow(() -> new EntityNotFoundException(Collection.class, "id", collectionId)));
    }

    @Override
    public boolean flashcardBelongTo(String jwt, Integer flashcardId) {
        return belongTo(jwt, userRepository.findUserIdByFlashcardId(flashcardId)
                .orElseThrow(() -> new EntityNotFoundException(Flashcard.class, "id", flashcardId)));
    }

    @Override
    public boolean formattedTextBelongTo(String jwt, Integer textId) {
        return belongTo(jwt, userRepository.findUserIdByTextId(textId)
                .orElseThrow(() -> new EntityNotFoundException(FormattedText.class, "id", textId)));
    }

    @Override
    public Integer getUserId(String jwt) {
        try {
            return Integer.parseInt(jwtService.getSubject(jwt));
        } catch (NumberFormatException e) {
            throw new JwtIllegalSubjectException("Jwt subject is not a User Id", e);
        }
    }

}
