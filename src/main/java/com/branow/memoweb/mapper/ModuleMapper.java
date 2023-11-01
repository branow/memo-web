package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.module.ModuleGeneralDetailsDto;
import com.branow.memoweb.dto.module.ModuleShortDetailsRepositoryDto;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModuleMapper {

    public ModuleGeneralDetailsDto toModuleSimpleDto(ModuleShortDetailsRepositoryDto module, List<CollectionShortDetailsDto> collections,
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
