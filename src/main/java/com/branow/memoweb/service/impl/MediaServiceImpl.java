package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.dto.media.MediaSaveDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.MediaMapper;
import com.branow.memoweb.model.Media;
import com.branow.memoweb.repository.MediaRepository;
import com.branow.memoweb.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

@RequiredArgsConstructor
@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository repository;
    private final MediaMapper mapper;


    @Override
    public Media getByMediaId(Integer mediaId) {
        return repository.findById(mediaId)
                .orElseThrow(() -> new EntityNotFoundException(Media.class, "id", mediaId));
    }

    @Override
    public MediaDetailsDto getDetailsDtoByMediaId(Integer mediaId) {
        return mapper.toMediaDetailsDto(repository.getShortDetailsRepositoryDtoByMediaId(mediaId));
    }

    @Override
    public MediaDetailsDto save(MediaSaveDto dto) {
        try {
            URL url = new URL(dto.getMediaUrl());
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(), baos);

            byte[] media = baos.toByteArray();
            return mapper.toMediaDetailsDto(repository.loadMediaIfNotExist(media, dto.getFormat()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
