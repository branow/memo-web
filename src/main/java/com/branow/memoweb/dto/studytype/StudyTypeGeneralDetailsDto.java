package com.branow.memoweb.dto.studytype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyTypeGeneralDetailsDto {

    private Integer studyId;
    private String studyType;

}
