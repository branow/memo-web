package com.branow.memoweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flashcardId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "front_side")
    private FormattedText frontSide;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "back_side")
    private FormattedText backSide;

    @OneToMany
    @JoinColumn(name = "flashcard")
    private List<Score> scores;

    private Integer collection;

}
