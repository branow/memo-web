package com.branow.memoweb.dto.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MediaDto {

    private Integer mediaId;
    private byte[] media;
    private String hash;

}
