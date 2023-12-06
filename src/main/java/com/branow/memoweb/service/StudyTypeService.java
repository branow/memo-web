package com.branow.memoweb.service;

import com.branow.memoweb.dto.studytype.StudyTypeDto;
import com.branow.memoweb.model.StudyType;
import org.springframework.stereotype.Service;

@Service
public interface StudyTypeService {

    StudyType getByStudyId(Integer studyId);
    StudyTypeDto getDtoByStudyName(String studyTypeName);

}
