package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.CollectionDetailsDto;
import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionMapper {


    public CollectionDetailsDto toCollectionDetailsDto(CollectionShortDetailsRepositoryDto dto,
                                                       List<ScoreAggregatedDto> scores,
                                                       List<Integer> flashcardIds) {
        return CollectionDetailsDto.builder()
                .collectionId(dto.getCollectionId())
                .collectionName(dto.getCollectionName())
                .size(dto.getSize())
                .flashcardIds(flashcardIds)
                .scores(scores)
                .build();
    }

    public CollectionGeneralDetailsDto toCollectionGeneralDetailsDto(CollectionShortDetailsRepositoryDto dto,
                                                                     List<ScoreAggregatedDto> scores) {
        return CollectionGeneralDetailsDto.builder()
                .collectionId(dto.getCollectionId())
                .collectionName(dto.getCollectionName())
                .size(dto.getSize())
                .scores(scores)
                .build();
    }

    public CollectionShortDetailsDto toCollectionShortDetailsDto(CollectionShortDetailsRepositoryDto repositoryDto) {
        return CollectionShortDetailsDto.builder()
                .collectionId(repositoryDto.getCollectionId())
                .collectionName(repositoryDto.getCollectionName())
                .size(repositoryDto.getSize())
                .build();
    }

}
