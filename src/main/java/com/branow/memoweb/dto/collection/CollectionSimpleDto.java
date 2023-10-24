package com.branow.memoweb.dto.collection;

import com.branow.memoweb.dto.score.ScoreSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionSimpleDto {

    private Integer collectionId;
    private String collectionName;
    private Integer size;
    private ScoreSimpleDto score;

}
