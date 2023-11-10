package com.branow.memoweb.repository;

import com.branow.memoweb.dto.flashcard.FlashcardShortDetailsRepositoryDto;
import com.branow.memoweb.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {

    @Query("select f.flashcardId from Flashcard f where f.collection = ?1")
    List<Integer> findFlashcardIdAllByCollectionId(Integer collectionId);

    List<Flashcard> findAllByCollection(Integer collectionId);

    @Query(value = "call get_flashcard_short_details_by_flashcard_id(?1)", nativeQuery = true)
    Optional<FlashcardShortDetailsRepositoryDto> findShortDetailsByFlashcardId(Integer flashcardId);
}
