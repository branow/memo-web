package com.branow.memoweb.repository;


import com.branow.memoweb.dto.score.ScoreParamsRepositoryDto;
import com.branow.memoweb.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Score.ScoreId> {

    @Query(value = "call get_score_average_params_all_by_module_id(?1)", nativeQuery = true)
    List<ScoreParamsRepositoryDto> findAverageParamsAllByModuleId(Integer moduleId);

    @Query(value = "call get_score_average_params_all_by_collection_id(?1)", nativeQuery = true)
    List<ScoreParamsRepositoryDto> findAverageParamsAllByCollectionId(Integer collectionId);

    @Query(value = "call get_score_params_all_by_flashcard_id(?1)", nativeQuery = true)
    List<ScoreParamsRepositoryDto> findParamsAllByFlashcardId(Integer flashcardId);

}
