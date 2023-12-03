package com.branow.memoweb.dto.collection;

import com.branow.memoweb.dto.module.ModuleShortDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.dto.user.UserPublicShortDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionDetailsDto {

    private Integer collectionId;
    private String collectionName;
    private Integer size;
    private List<Integer> flashcardIds;
    private List<ScoreAggregatedDto> scores;
    private UserPublicShortDetailsDto owner;
    private ModuleShortDetailsDto module;

}
