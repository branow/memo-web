package com.branow.memoweb.dto.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaSaveDto {

    private Integer mediaId;
    private String mediaUrl;
    private String format;

}
