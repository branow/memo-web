package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionShortDto;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository repository;

    @Override
    public List<CollectionShortDto> getShortDtoAllByModuleId(Integer moduleId) {
        return repository.findCollectionShortDtoAllByModuleId(moduleId);
    }

}
