package com.branow.memoweb.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.dto.media.MediaShortDetailsRepositoryDto;
import com.branow.memoweb.model.Media;
import com.branow.memoweb.model.auxilary.FileType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MediaMapper {

    private final FileFormatMapper fileFormatMapper;
    @Value("${server.port}")
    private String serverPort;
    @Value("${host}")
    private String serverAddress;


    public MediaDetailsDto toMediaDetailsDto(Media entity) {
        return MediaDetailsDto.builder()
                .mediaId(entity.getMediaId())
                .mediaUrl(toMediaUrl(entity.getMediaId(), entity.getFormat()))
                .build();
    }

    public MediaDetailsDto toMediaDetailsDto(MediaShortDetailsRepositoryDto dto) {
        return MediaDetailsDto.builder()
                .mediaId(dto.getMediaId())
                .mediaUrl(toMediaUrl(dto.getMediaId(), FileType.Format.valueOf(dto.getFormat())))
                .build();
    }

    private String toMediaUrl(Integer mediaId, FileType.Format mediaFormat) {
        String id = "" + mediaId;
        String format = "" + fileFormatMapper
                .toFileType(mediaFormat)
                .toString().toLowerCase();
        String controller = "media";
        return String.format("http://%s:%s/%s/%s/%s", serverAddress, serverPort, controller, format, id);
    }

}
