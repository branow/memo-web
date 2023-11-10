package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.dto.media.MediaSaveDto;
import com.branow.memoweb.dto.media.MediaShortDetailsRepositoryDto;
import com.branow.memoweb.model.Media;
import com.branow.memoweb.model.auxilary.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

import static com.brano.print.ShortPrint.form;

@RequiredArgsConstructor
@Service
public class MediaMapper {

    private final FileFormatMapper fileFormatMapper;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.address}")
    private String serverAddress;


    public MediaDetailsDto toMediaDetailsDto(MediaShortDetailsRepositoryDto dto) {
        return MediaDetailsDto.builder()
                .mediaId(dto.getMediaId())
                .mediaUrl(toMediaUrl(dto.getMediaId(), FileType.Format.valueOf(dto.getFormat())))
                .build();
    }

    private String toMediaUrl(Integer mediaId, FileType.Format mediaFormat) {
        String id = "/" + mediaId;
        String format = "/" + fileFormatMapper
                .toFileType(mediaFormat)
                .toString().toLowerCase();
        String controller = "/media";
        return form().and("http://").and(serverAddress).and(":").and(serverPort)
                .and(controller).and(format).and(id).toString();
    }

}
