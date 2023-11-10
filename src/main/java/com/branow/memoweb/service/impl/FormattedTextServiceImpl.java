package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.flashcard.FlashcardSaveDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.FormattedTextMapper;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.repository.FormattedTextRepository;
import com.branow.memoweb.service.FormattedTextService;
import com.branow.memoweb.service.JwtBelongingChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormattedTextServiceImpl implements FormattedTextService {

    private final FormattedTextRepository repository;
    private final FormattedTextMapper mapper;
    private final JwtBelongingChecker jwtBelongingChecker;

    @Override
    public FormattedText getByTextId(Integer textId) {
        return repository.findById(textId)
                .orElseThrow(() -> new EntityNotFoundException(FormattedText.class, "id", textId));
    }

    @Override
    public FormattedTextGeneralDetailsDto getGeneralDetailsDtoByTextId(Integer textId) {
        return mapper.toFormattedTextGeneralDetailsDto(repository.findGeneralDetailsByTextId(textId)
                .orElseThrow(() -> new EntityNotFoundException(FormattedText.class, "id", textId)));
    }

    @Override
    public FormattedText createEmpty() {
        return repository.save(FormattedText.builder()
                .text("")
                .build());
    }

    @Override
    public FormattedTextSaveDto saveWithJwtCheck(String jwt, FormattedTextSaveDto dto) {
        jwtBelongingChecker.formattedTextBelongToOrThrow(jwt, dto.getTextId());
        return mapper.toFormattedTextSaveDto(repository.save(mapper.toFormattedText(dto)));
    }

}
