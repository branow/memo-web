package com.branow.memoweb.dto.formattedtext;

import com.branow.memoweb.model.auxilary.TextFormat;
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
    private TextFormat format;
    private Integer imageId;
    private Integer audioId;

}
