package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsRepositoryDto;
import com.branow.memoweb.service.TextFormatConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormattedTextMapper {

    private final TextFormatConverter converter;


    public FormattedTextGeneralDetailsDto toFormattedTextGeneralDetailsDto(FormattedTextGeneralDetailsRepositoryDto dto) {
        return FormattedTextGeneralDetailsDto.builder()
                .textId(dto.getTextId())
                .text(dto.getText())
                .format(converter.toTextFormat(dto.getFormat()))
                .audioId(dto.getAudioId())
                .imageId(dto.getImageId())
                .build();
    }

}
