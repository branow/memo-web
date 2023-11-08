package com.branow.memoweb.service;

import com.branow.memoweb.dto.collection.CollectionGeneralDetailsDto;
import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import com.branow.memoweb.model.Collection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectionService {
    List<CollectionShortDetailsDto> getShortDetailsDtoAllByModuleId(Integer moduleId);

    List<CollectionGeneralDetailsDto> getGeneralDetailsDtoAllByModuleId(Integer moduleId);

    List<Collection> getAllByModuleId(Integer moduleId);
}
