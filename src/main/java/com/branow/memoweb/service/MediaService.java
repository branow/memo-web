package com.branow.memoweb.service;

import com.branow.memoweb.dto.media.MediaDetailsDto;
import com.branow.memoweb.model.Media;

public interface MediaService {

    Media getByMediaId(Integer mediaId);

    MediaDetailsDto getDetailsDtoByMediaId(Integer mediaId);

}
