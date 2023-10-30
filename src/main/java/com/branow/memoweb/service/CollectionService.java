package com.branow.memoweb.service;

import com.branow.memoweb.dto.collection.CollectionShortDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectionService {
    List<CollectionShortDto> getCollectionShortDtoAllByModuleId(Integer moduleId);

}
