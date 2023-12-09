package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.dto.module.*;
import com.branow.memoweb.dto.score.ScoreAggregatedDto;
import com.branow.memoweb.dto.user.UserPublicShortDetailsRepositoryDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.ModuleMapper;
import com.branow.memoweb.model.Collection;
import com.branow.memoweb.model.Flashcard;
import com.branow.memoweb.model.Module;
import com.branow.memoweb.model.User;
import com.branow.memoweb.repository.ModuleRepository;
import com.branow.memoweb.repository.UserRepository;
import com.branow.memoweb.service.CollectionService;
import com.branow.memoweb.service.JwtBelongingChecker;
import com.branow.memoweb.service.ModuleService;
import com.branow.memoweb.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository repository;
    private final ModuleMapper mapper;
    private final CollectionService collectionService;
    private final ScoreService scoreService;
    private final JwtBelongingChecker jwtBelongingChecker;
    private final UserRepository userRepository;

    @Override
    public List<ModuleCollectionDto> getCollectionDtoAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId).stream()
                .map(mapper::toModuleCollectionDto).toList();
    }

    @Override
    public Module getByModuleId(Integer moduleId) {
        return repository.findById(moduleId)
                .orElseThrow(() -> new EntityNotFoundException(Module.class, "id", moduleId));
    }

    @Override
    public Module save(Module module) {
        List<Collection> collections = module.getCollections();
        module.setCollections(List.of());
        repository.save(module);
        if (collections != null) {
            for (Collection collection: collections) {
                collection.setModule(module.getModuleId());
                collectionService.save(collection);
            }
        }
        module.setCollections(collections);
        return module;
    }

    @Override
    public ModuleSaveDto saveByUserId(Integer userId, ModuleSaveDto dto) {
        Module newMod = repository.save(mapper.toModule(dto, userId));
        return mapper.toModuleSaveDto(newMod);
    }

    @Override
    public ModuleSaveDto saveToJwtUser(String jwt, ModuleSaveDto dto) {
        Integer userId = jwtBelongingChecker.getUserId(jwt);
        return saveByUserId(userId, dto);
    }

    @Override
    public ModuleDetailsDto getDetailsDtoByModuleId(Integer moduleId) {
        ModuleDetailsRepositoryDto dto = repository.findDetailsByModuleId(moduleId)
                .orElseThrow(() -> new EntityNotFoundException(Module.class, "id", moduleId));
        UserPublicShortDetailsRepositoryDto owner = userRepository.findUserPublicShortDetailsByModuleId(moduleId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "moduleId", moduleId));
        List<CollectionGeneralDetailsDto> collections = collectionService.getGeneralDetailsDtoAllByModuleId(moduleId);
        List<ScoreAggregatedDto> scores = scoreService.getAggregatedDtoAllByModuleId(moduleId);
        return mapper.toModuleDetailsDto(dto, collections, scores, owner);
    }

    @Override
    public ModuleGeneralDetailsDto getGeneralDetailsDtoByModuleId(Integer id) {
        ModuleShortDetailsRepositoryDto dto = repository.findShortDetailsByModuleId(id)
                .orElseThrow(() -> new EntityNotFoundException(Module.class, "id", id));
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
        return repository.findAllByUser(userId);
    }

    @Override
    public void deleteByModuleIdWithJwtCheck(String jwt, Integer moduleId) {
        jwtBelongingChecker.moduleBelongToOrThrow(jwt, moduleId);
        repository.deleteByModuleId(moduleId);
    }

}
