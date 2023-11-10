package com.branow.memoweb.repository;

import com.branow.memoweb.dto.formattedtext.FormattedTextGeneralDetailsRepositoryDto;
import com.branow.memoweb.model.FormattedText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FormattedTextRepository extends JpaRepository<FormattedText, Integer> {

    @Query(value = "call get_formatted_text_general_details_by_text_id(?1)", nativeQuery = true)
    Optional<FormattedTextGeneralDetailsRepositoryDto> findGeneralDetailsByTextId(Integer textId);

}
