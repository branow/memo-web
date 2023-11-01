package com.branow.memoweb.repository;

import com.branow.memoweb.model.StudyType;
import com.branow.memoweb.model.auxilary.StudyTypeName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyTypeRepository extends JpaRepository<StudyType, Integer> {

    Optional<StudyType> findByStudyName(StudyTypeName studyTypeName);

}
