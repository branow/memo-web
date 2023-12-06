package com.branow.memoweb.repository;


import com.branow.memoweb.dto.score.ScoreParamsRepositoryDto;
import com.branow.memoweb.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Score.ScoreId> {

    @Query("select s from Score s where s.scoreId.flashcard = :flashcard and s.scoreId.studyType.studyId = :study_type")
    Optional<Score> findByFlashcardAndStudyType(@Param("flashcard") Integer flashcard,@Param("study_type") Integer studyType);

    @Query("select s from Score s where s.scoreId.flashcard = ?1")
    List<Score> findAllByFlashcard(Integer flashcardId);

    @Query(value = "call get_score_average_params_all_by_module_id(?1)", nativeQuery = true)
    List<ScoreParamsRepositoryDto> findAverageParamsAllByModuleId(Integer moduleId);

    @Query(value = "call get_score_average_params_all_by_collection_id(?1)", nativeQuery = true)
    List<ScoreParamsRepositoryDto> findAverageParamsAllByCollectionId(Integer collectionId);

    @Query(value = "call get_score_params_all_by_flashcard_id(?1)", nativeQuery = true)
    List<ScoreParamsRepositoryDto> findParamsAllByFlashcardId(Integer flashcardId);

}
