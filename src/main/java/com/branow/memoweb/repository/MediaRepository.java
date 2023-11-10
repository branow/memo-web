package com.branow.memoweb.repository;

import com.branow.memoweb.dto.media.MediaShortDetailsRepositoryDto;
import com.branow.memoweb.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    @Query(value = "call get_media_short_details_by_media_id(?1)", nativeQuery = true)
    MediaShortDetailsRepositoryDto getShortDetailsRepositoryDtoByMediaId(Integer mediaId);


    @Query(value = "call load_media_if_not_exist(?1, ?2)", nativeQuery = true)
    MediaShortDetailsRepositoryDto loadMediaIfNotExist(byte[] media, String format);

}
