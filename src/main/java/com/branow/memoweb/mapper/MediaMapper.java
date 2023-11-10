package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.dto.media.MediaShortDetailsRepositoryDto;
import com.branow.memoweb.model.auxilary.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
        String id = "/" + dto.getMediaId();
        String format = "/" + fileFormatMapper
                .toFileType(FileType.Format.valueOf(dto.getFormat()))
                .toString().toLowerCase();
        String controller = "/media";
        String url = form().and("http://").and(serverAddress).and(":").and(serverPort)
                .and(controller).and(format).and(id).toString();
        return MediaDetailsDto.builder()
                .mediaId(dto.getMediaId())
                .mediaUrl(url)
                .build();
    }

}
