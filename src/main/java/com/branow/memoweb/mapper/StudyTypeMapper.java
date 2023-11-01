package com.branow.memoweb.mapper;

import com.branow.memoweb.dto.studytype.StudyTypeDto;
import com.branow.memoweb.model.StudyType;
import org.springframework.stereotype.Service;

@Service
public class StudyTypeMapper {

    public StudyTypeDto toStudyTypeDto(StudyType studyType) {
        return StudyTypeDto.builder()
                .studyId(studyType.getStudyId())
                .studyName(studyType.getStudyName())
                .build();
    }

}
