package com.branow.memoweb.dto.module;

import com.branow.memoweb.dto.accesstype.AccessTypeDto;
import com.branow.memoweb.dto.collection.CollectionDto;
import com.branow.memoweb.dto.score.ScoreSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuleSimpleDto {

    private Integer moduleId;
    private String moduleName;
    private String shortDescription;
    private AccessTypeDto accessType;
    private List<CollectionDto> collections;
    private Integer sizeCard;
    private Integer sizeCollection;
    private ScoreSimpleDto score;

}
