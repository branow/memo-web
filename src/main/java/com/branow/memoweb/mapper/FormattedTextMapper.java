package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Media;
import com.branow.memoweb.service.MediaService;
import com.branow.memoweb.service.TextFormatConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormattedTextMapper {

    private final TextFormatConverter converter;
    private final MediaService mediaService;


    public FormattedTextSaveDto toFormattedTextSaveDto(FormattedText text) {
        Integer audioId = text.getAudio() != null ? text.getAudio().getMediaId() : null;
        Integer imageId = text.getImage() != null ? text.getImage().getMediaId() : null;
        return FormattedTextSaveDto.builder()
                .textId(text.getTextId())
                .text(text.getText())
                .format(text.getFormat())
                .audioId(audioId)
                .imageId(imageId)
                .build();
    }

    public FormattedText toFormattedText(FormattedTextSaveDto dto) {
        Media audio = dto.getAudioId() != null ? mediaService.getByMediaId(dto.getAudioId()) : null;
        Media image = dto.getImageId() != null ? mediaService.getByMediaId(dto.getImageId()) : null;
        return FormattedText.builder()
                .textId(dto.getTextId())
                .text(dto.getText())
                .format(dto.getFormat())
                .audio(audio)
                .image(image)
                .build();
    }

    public FormattedTextGeneralDetailsDto toFormattedTextGeneralDetailsDto(FormattedTextGeneralDetailsRepositoryDto dto) {
        MediaDetailsDto audio = dto.getAudioId() != null ? mediaService.getDetailsDtoByMediaId(dto.getAudioId()) : null;
        MediaDetailsDto image = dto.getImageId() != null ? mediaService.getDetailsDtoByMediaId(dto.getImageId()) : null;
        return FormattedTextGeneralDetailsDto.builder()
                .textId(dto.getTextId())
                .text(dto.getText())
                .format(converter.toTextFormat(dto.getFormat()))
                .audio(audio)
                .image(image)
                .build();
    }

}
