package com.branow.memoweb.service;

import com.branow.memoweb.dto.collection.CollectionShortDetailsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectionService {
    List<CollectionShortDetailsDto> getShortDetailsDtoAllByModuleId(Integer moduleId);

}
