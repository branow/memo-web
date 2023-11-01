package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.repository.CollectionRepository;
import org.springframework.stereotype.Service;

@Service
public class CollectionMapper {

    public CollectionShortDetailsDto toCollectionShortDetailsDto(CollectionShortDetailsRepositoryDto repositoryDto) {
        return CollectionShortDetailsDto.builder()
                .collectionId(repositoryDto.getCollectionId())
                .collectionName(repositoryDto.getCollectionName())
                .size(repositoryDto.getSize())
                .build();
    }

}
