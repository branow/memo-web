package com.branow.memoweb.dto.studytype;

import com.branow.memoweb.model.auxilary.StudyTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyTypeDto {

    private Integer studyTypeId;
    private StudyTypeName studyTypeName;

}
