package com.branow.memoweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Score {

    @EmbeddedId
    private ScoreId scoreId;
    private Integer score;
    private LocalDateTime studyTime;
    private LocalDateTime resetTime;
    private Integer studyRepetition;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Embeddable
    public static class ScoreId implements Serializable {

        private Integer flashcard;
        @ManyToOne
        @JoinColumn(name = "studyType")
        private StudyType studyType;

    }

}
