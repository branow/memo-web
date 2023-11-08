package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.module.ModuleDetailsDto;
import com.branow.memoweb.dto.module.ModuleDetailsRepositoryDto;
import com.branow.memoweb.dto.module.ModuleGeneralDetailsDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModuleMapper {

    public ModuleDetailsDto toModuleDetailsDto(ModuleDetailsRepositoryDto module, List<CollectionGeneralDetailsDto> collections,
                                               List<ScoreAggregatedDto> scores) {
        return ModuleDetailsDto.builder()
                .moduleId(module.getModuleId())
                .moduleName(module.getModuleName())
                .description(module.getDescription())
                .access(module.getAccess())
                .collections(collections)
                .scores(scores)
                .build();
    }

    public ModuleGeneralDetailsDto toModuleGeneralDetailsDto(ModuleShortDetailsRepositoryDto module, List<CollectionShortDetailsDto> collections,
                                                             List<ScoreAggregatedDto> scores) {
        return ModuleGeneralDetailsDto.builder()
                .moduleId(module.getModuleId())
                .moduleName(module.getModuleName())
                .shortDescription(module.getShortDescription())
                .access(module.getAccess())
                .collections(collections)
                .scores(scores)
                .build();
    }

}
