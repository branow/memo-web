package com.branow.memoweb.repository;

import com.branow.memoweb.dto.flashcard.FlashcardScoreParamsRepositoryDto;
import com.branow.memoweb.dto.flashcard.FlashcardShortDetailsRepositoryDto;
import com.branow.memoweb.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {

    @Query("select f.flashcardId from Flashcard f where f.collection = ?1")
    List<Integer> findFlashcardIdAllByCollectionId(Integer collectionId);

    @Query(value = "call get_flashcard_score_all_by_collection_id(:collection, :study_type)", nativeQuery = true)
    List<FlashcardScoreParamsRepositoryDto> findFlashcardScoreAllByCollectionIdAndStudyTypeId(
            @Param("collection") Integer collectionId, @Param("study_type") Integer studyTypeId);

    List<Flashcard> findAllByCollection(Integer collectionId);

    @Query(value = "call get_flashcard_short_details_by_flashcard_id(?1)", nativeQuery = true)
    Optional<FlashcardShortDetailsRepositoryDto> findShortDetailsByFlashcardId(Integer flashcardId);

    @Transactional
    @Modifying
    @Query(value = "delete from flashcard f where f.flashcard_id = ?1", nativeQuery = true)
    void deleteByFlashcardId(Integer flashcardId);
}
