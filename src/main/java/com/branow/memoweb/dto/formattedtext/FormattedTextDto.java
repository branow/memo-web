package com.branow.memoweb.dto.formattedtext;

import com.branow.memoweb.dto.media.MediaDto;
import com.branow.memoweb.model.auxilary.TextFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormattedTextDto {

    private Integer textId;
    private String text;
    private TextFormat format;
    private MediaDto image;
    private MediaDto audio;

}
