package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.module.*;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.model.AccessType;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.model.Module;
import com.branow.memoweb.service.AccessTypeService;
import com.branow.memoweb.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModuleMapper {

    private final CollectionService collectionService;
    private final AccessTypeService accessTypeService;


    public ModuleSaveDto toModuleSaveDto(Module module) {
        return ModuleSaveDto.builder()
                .moduleId(module.getModuleId())
                .moduleName(module.getModuleName())
                .access(module.getAccessType().getAccess().toString())
                .description(module.getDescription())
                .build();
    }

    public Module toModule(ModuleSaveDto dto, Integer userId) {
        List<Collection> collections = collectionService.getAllByModuleId(dto.getModuleId());
        AccessType accessType = accessTypeService.getByAccessName(dto.getAccess());
        return Module.builder()
                .moduleId(dto.getModuleId())
                .moduleName(dto.getModuleName())
                .description(dto.getDescription())
                .collections(collections)
                .accessType(accessType)
                .user(userId)
                .build();
    }

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
