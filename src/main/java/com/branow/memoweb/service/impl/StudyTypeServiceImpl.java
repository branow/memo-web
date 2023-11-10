package com.branow.memoweb.service.impl;

import com.branow.memoweb.dto.studytype.StudyTypeDto;
import com.branow.memoweb.exception.EntityNotFoundException;
import com.branow.memoweb.mapper.StudyTypeMapper;
import com.branow.memoweb.model.StudyType;
import com.branow.memoweb.model.auxilary.StudyTypeName;
import com.branow.memoweb.repository.StudyTypeRepository;
import com.branow.memoweb.service.StudyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudyTypeServiceImpl implements StudyTypeService {

    private final StudyTypeRepository repository;
    private final StudyTypeMapper mapper;

    @Override
    public StudyTypeDto getByStudyName(String studyName) {
        return mapper.toStudyTypeDto(repository.findByStudyName(StudyTypeName.valueOf(studyName))
                .orElseThrow(() -> new EntityNotFoundException(StudyType.class, "study type name", studyName)));
    }

}
