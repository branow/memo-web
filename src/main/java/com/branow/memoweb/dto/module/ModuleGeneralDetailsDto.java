package com.branow.memoweb.dto.module;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleGeneralDetailsDto {

    private Integer moduleId;
    private String moduleName;
    private String shortDescription;
    private String access;
    private List<CollectionShortDetailsDto> collections;
    private List<ScoreAggregatedDto> scores;

}
