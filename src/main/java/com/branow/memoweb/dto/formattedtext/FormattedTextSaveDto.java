package com.branow.memoweb.dto.formattedtext;

import com.branow.memoweb.dto.media.MediaSaveDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormattedTextSaveDto {

    private Integer textId;
    private String text;
    private String format;
    private MediaSaveDto image;
    private MediaSaveDto audio;

}
