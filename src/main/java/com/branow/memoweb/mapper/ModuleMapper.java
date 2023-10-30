package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.CollectionShortDto;
import com.branow.memoweb.dto.module.ModuleShortDto;
import com.branow.memoweb.dto.module.ModuleSimpleDto;
import com.branow.memoweb.dto.score.ScoreSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModuleMapper {

    public ModuleSimpleDto toModuleSimpleDto(ModuleShortDto module, List<CollectionShortDto> collections,
                                             List<ScoreSimpleDto> scores) {
        return ModuleSimpleDto.builder()
                .moduleId(module.getModuleId())
                .moduleName(module.getModuleName())
                .shortDescription(module.getShortDescription())
                .access(module.getAccess())
                .collections(collections)
                .scores(scores)
                .build();
    }

}
