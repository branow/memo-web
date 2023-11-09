package com.branow.memoweb.repository;

import com.branow.memoweb.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {

    @Query("select f.flashcardId from Flashcard f where f.collection = ?1")
    List<Integer> findFlashcardIdAllByCollectionId(Integer collectionId);

}
