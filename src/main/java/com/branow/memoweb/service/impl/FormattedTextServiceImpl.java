package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.formattedtext.FormattedTextDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.FormattedTextMapper;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Media;
import com.branow.memoweb.repository.FormattedTextRepository;
import com.branow.memoweb.service.FormattedTextService;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormattedTextServiceImpl implements FormattedTextService {

    private final FormattedTextRepository repository;
    private final FormattedTextMapper mapper;
    private final MediaService mediaService;

    @Override
    public FormattedText getByTextId(Integer textId) {
        return repository.findById(textId)
                .orElseThrow(() -> new EntityNotFoundException(FormattedText.class, "id", textId));
    }

    @Override
    public FormattedTextDetailsDto getDetailsDtoByTextId(Integer textId) {
        FormattedTextGeneralDetailsRepositoryDto dto = repository.findGeneralDetailsByTextId(textId)
                .orElseThrow(() -> new EntityNotFoundException(FormattedText.class, "id", textId));
        MediaDetailsDto audio = dto.getAudioId() != null ? mediaService.getDetailsDtoByMediaId(dto.getAudioId()) : null;
        MediaDetailsDto image = dto.getImageId() != null ? mediaService.getDetailsDtoByMediaId(dto.getImageId()) : null;
        return mapper.toFormattedTextDetailsDto(dto, audio, image);
    }

    @Override
    public FormattedText save(FormattedTextSaveDto dto) {
        Media audio = dto.getAudio() != null ? mediaService.save(dto.getAudio()) : null;
        Media image = dto.getImage() != null ? mediaService.save(dto.getImage()) : null;
        return repository.save(mapper.toFormattedText(dto, audio, image));
    }

}
