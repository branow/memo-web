package com.branow.memoweb.service;

import com.branow.memoweb.dto.studytype.StudyTypeDto;
import org.springframework.stereotype.Service;

@Service
public interface StudyTypeService {

    StudyTypeDto getByStudyName(String studyTypeName);

}
