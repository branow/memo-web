package com.branow.memoweb.dto.formattedtext;

import com.branow.memoweb.dto.media.MediaDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormattedTextDetailsDto {

    private Integer textId;
    private String text;
    private String format;
    private MediaDetailsDto image;
    private MediaDetailsDto audio;

}
