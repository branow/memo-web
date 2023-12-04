package com.branow.memoweb.dto.module;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
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
public class ModuleDetailsDto {

    private Integer moduleId;
    private String moduleName;
    private String description;
    private String access;
    private List<CollectionGeneralDetailsDto> collections;
    private List<ScoreAggregatedDto> scores;
    private UserPublicShortDetailsDto owner;

}
