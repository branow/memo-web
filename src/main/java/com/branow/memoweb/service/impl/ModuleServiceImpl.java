package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.module.*;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.exception.entitynotfound.ModuleNotFoundException;
import com.branow.memoweb.mapper.ModuleMapper;
import com.branow.memoweb.model.Module;
import com.branow.memoweb.repository.ModuleRepository;
import com.branow.memoweb.service.CollectionService;
import com.branow.memoweb.service.ModuleService;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository repository;
    private final ModuleMapper mapper;
    private final CollectionService collectionService;
    private final ScoreService scoreService;
    private final JwtTokenService jwtTokenService;

    @Override
    public ModuleSaveDto saveByUserId(Integer userId, ModuleSaveDto dto) {
        Module newMod = repository.save(mapper.toModule(dto, userId));
        return mapper.toModuleSaveDto(newMod);
    }

    @Override
    public ModuleSaveDto saveByJwtToken(String jwtToken, ModuleSaveDto dto) {
        Integer userId = jwtTokenService.getUserId(jwtToken);
        return saveByUserId(userId, dto);
    }

    @Override
    public ModuleDetailsDto getDetailsDtoByModuleId(Integer id) {
        ModuleDetailsRepositoryDto dto = repository.findDetailsByModuleId(id)
                .orElseThrow(() -> new ModuleNotFoundException("id", id));
        List<CollectionGeneralDetailsDto> collections = collectionService.getGeneralDetailsDtoAllByModuleId(id);
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByModuleId(id);
        return mapper.toModuleDetailsDto(dto, collections, scores);
    }

    @Override
    public ModuleGeneralDetailsDto getGeneralDetailsDtoByModuleId(Integer id) {
        ModuleShortDetailsRepositoryDto dto = repository.findShortDetailsByModuleId(id)
                .orElseThrow(() -> new ModuleNotFoundException("id", id));
        List<CollectionShortDetailsDto> collections = collectionService.getShortDetailsDtoAllByModuleId(id);
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByModuleId(id);
        return mapper.toModuleGeneralDetailsDto(dto, collections, scores);
    }

    @Override
    public List<Integer> getModuleIdAllByUserId(Integer userId) {
        return repository.findModuleIdAllByUserId(userId);
    }

    @Override
    public List<Integer> getModuleIdWithPublicAccessAllByUserId(Integer userId) {
        return repository.findModuleIdWithPublicAccessAllByUserId(userId);
    }

    @Override
    public List<Module> getAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public void deleteByJwtTokenAndModuleId(String jwtToken, Integer moduleId) {
        Integer userId = jwtTokenService.getUserId(jwtToken);
        Integer ownerId = repository.findUserByModuleId(moduleId)
                .orElseThrow(() -> new ModuleNotFoundException("id", moduleId));
        if (!userId.equals(ownerId)) {
            throw new IllegalStateException("Jwt Token belongs to another User than Module");
        }
        repository.deleteById(moduleId);
    }

}
