package com.branow.memoweb.repository;

import com.branow.memoweb.dto.collection.CollectionShortDetailsRepositoryDto;
import com.branow.memoweb.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    @Query(value = "call get_collection_short_details_all_by_module_id(?1)", nativeQuery = true)
    List<CollectionShortDetailsRepositoryDto> findShortDetailsAllByModuleId(Integer moduleId);

    @Query(value = "call get_collection_short_details_by_collection_id(?1)", nativeQuery = true)
    Optional<CollectionShortDetailsRepositoryDto> findShortDetailsByCollectionId(Integer collectionId);

    @Query(value = "call get_collection_short_details_by_flashcard_id(?1)", nativeQuery = true)
    Optional<CollectionShortDetailsRepositoryDto> findShortDetailsByFlashcardId(Integer flashcardId);

    List<Collection> findAllByModule(Integer moduleId);

    @Transactional
    @Modifying
    @Query("delete from Collection where collectionId = ?1")
    void deleteByCollectionId(Integer collectionId);

}
