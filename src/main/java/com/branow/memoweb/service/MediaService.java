package com.branow.memoweb.service;

import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.dto.media.MediaSaveDto;
import com.branow.memoweb.model.Media;

public interface MediaService {

    Media getByMediaId(Integer mediaId);

    MediaDetailsDto getDetailsDtoByMediaId(Integer mediaId);

    MediaDetailsDto save(MediaSaveDto dto);

}
