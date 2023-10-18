package com.branow.memoweb.model;


import com.branow.memoweb.model.auxilary.StudyTypeName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class StudyType {

    @Id
    private Integer studyTypeId;

    @Enumerated(EnumType.STRING)
    private StudyTypeName studyTypeName;

}
