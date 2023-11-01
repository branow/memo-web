package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.mapper.CollectionMapper;
import com.branow.memoweb.repository.CollectionRepository;
import com.branow.memoweb.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository repository;
    private final CollectionMapper mapper;

    @Override
    public List<CollectionShortDetailsDto> getShortDetailsDtoAllByModuleId(Integer moduleId) {
        return repository.findCollectionShortDetailsDtoAllByModuleId(moduleId).stream()
                .map(mapper::toCollectionShortDetailsDto).toList();
    }

}
