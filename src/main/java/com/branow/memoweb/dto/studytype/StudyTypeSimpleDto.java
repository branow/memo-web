package com.branow.memoweb.dto.studytype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyTypeSimpleDto {

    private Integer studyTypeId;
    private String studyType;

}
