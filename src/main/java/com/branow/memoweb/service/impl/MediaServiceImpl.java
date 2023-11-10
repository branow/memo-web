package com.branow.memoweb.service.impl;

import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.model.Media;
import com.branow.memoweb.repository.MediaRepository;
import com.branow.memoweb.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository repository;


    @Override
    public Media getByMediaId(Integer mediaId) {
        return repository.findById(mediaId)
                .orElseThrow(() -> new EntityNotFoundException(Media.class, "id", mediaId));
    }

}
