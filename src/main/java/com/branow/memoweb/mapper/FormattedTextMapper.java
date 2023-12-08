package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.formattedtext.FormattedTextDetailsDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsRepositoryDto;
import com.branow.memoweb.dto.formattedtext.FormattedTextSaveDto;
import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.model.FormattedText;
import com.branow.memoweb.model.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FormattedTextMapper {

    private final MediaMapper mediaMapper;


    public FormattedTextDetailsDto toFormattedTextDetailsDto(FormattedText text) {
        MediaDetailsDto audio = text.getAudio() != null ? mediaMapper.toMediaDetailsDto(text.getAudio()) : null;
        MediaDetailsDto image = text.getImage() != null ? mediaMapper.toMediaDetailsDto(text.getImage()) : null;
        return FormattedTextDetailsDto.builder()
                .textId(text.getTextId())
                .text(text.getText())
                .format(text.getFormat())
                .audio(audio)
                .image(image)
                .build();
    }

    public FormattedText toFormattedText(FormattedTextSaveDto dto, Media audio, Media image) {

        return FormattedText.builder()
                .textId(dto.getTextId())
                .text(dto.getText())
                .format(dto.getFormat())
                .audio(audio)
                .image(image)
                .build();
    }

    public FormattedTextDetailsDto toFormattedTextDetailsDto(FormattedTextGeneralDetailsRepositoryDto dto,
                                                             MediaDetailsDto audio, MediaDetailsDto image) {
        return FormattedTextDetailsDto.builder()
                .textId(dto.getTextId())
                .text(dto.getText())
                .format(dto.getFormat())
                .audio(audio)
                .image(image)
                .build();
    }

}
