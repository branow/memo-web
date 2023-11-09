package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.exception.entitynotfound.FormattedTextNotFoundException;
import com.branow.memoweb.mapper.FormattedTextMapper;
import com.branow.memoweb.repository.FormattedTextRepository;
import com.branow.memoweb.service.FormattedTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormattedTextServiceImpl implements FormattedTextService {

    private final FormattedTextRepository repository;
    private final FormattedTextMapper mapper;


    @Override
    public FormattedTextGeneralDetailsDto getFormattedTextGeneralDetailsDtoByTextId(Integer textId) {
        return mapper.toFormattedTextGeneralDetailsDto(repository.findGeneralDetailsByTextId(textId)
                .orElseThrow(() -> new FormattedTextNotFoundException("id", textId)));
    }

}
