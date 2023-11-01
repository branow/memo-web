package com.branow.memoweb.repository;


import com.branow.memoweb.dto.score.ScoreParamsRepositoryDto;
import com.branow.memoweb.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Score.ScoreId> {

    @Query(value = "call get_avg_score_for_module(?1)", nativeQuery = true)
    List<ScoreParamsRepositoryDto> findScoreAverageParamByModuleId(Integer moduleId);

}
